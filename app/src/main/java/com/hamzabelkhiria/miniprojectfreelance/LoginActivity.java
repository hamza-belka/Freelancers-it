package com.hamzabelkhiria.miniprojectfreelance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



        import android.app.ProgressDialog;
        import android.content.Intent;
import android.util.Log;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Employer.DashboardEmployer;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.DashboardFreelancer;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
        import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "usermail";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";
    private static final String KEY_USERID = "id";
    private static final String KEY_EMPTY = "";
    private static final String KEY_IMAGE = "image";
    private EditText etUsername;
    private EditText etPassword;
    private String username;
    private String password;
    private ProgressDialog pDialog;
    Generaladress g = new Generaladress();
    private String login_url = g.URLfirstpart+"/login.php";
    private SessionHandler session;
    String t ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());

        if(session.isLoggedIn()){
            loadDashboard();

        }
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etLoginPassword);

        Button register = findViewById(R.id.btnLoginRegister);
        Button login = findViewById(R.id.btnLogin);

        //Launch Registration screen when Register Button is clicked
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RolesChoix.class);
                startActivity(i);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                if (validateInputs()) {
                    login();
                }
            }
        });
    }

    private void settoken(int userid) {





        String Urlsettoken=g.URLfirstpart+"/settoken.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", userid);
            rq.put("token",FirebaseInstanceId.getInstance().getToken() );



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlsettoken,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", "["+response.toString()+"]");

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.getInt(KEY_STATUS) == 0) {
                                Log.d("updating token", jsonObject.getString(KEY_MESSAGE));

                            }else{
                                Log.d("updating token", jsonObject.getString(KEY_MESSAGE));


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("response", "["+response+"]");

                        }











                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("error1",error.getMessage());

            }
        });

        Volley.newRequestQueue(this).add(request);




    }

    /**
     * Launch Dashboard Activity on Successful Login
     */
    private void loadDashboard() {
        User user= session.getUserDetails();
        settoken(user.getUserid());
        Intent i;
        if (user.getRole().equals("freelancer")) {
             i = new Intent(getApplicationContext(), DashboardFreelancer.class);
        }else{
            i = new Intent(getApplicationContext(), DashboardEmployer.class);

        }

        startActivity(i);
        finish();

    }

    /**
     * Display Progress bar while Logging in
     */

    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void login() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, login_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully

                            if (response.getInt(KEY_STATUS) == 0) {
                                session.loginUser(username,response.getString(KEY_FULL_NAME),response.getString(KEY_ROLE),response.getInt(KEY_USERID),response.getString(KEY_IMAGE));
                                loadDashboard();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if(KEY_EMPTY.equals(username)){
            etUsername.setError("Username cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }
}