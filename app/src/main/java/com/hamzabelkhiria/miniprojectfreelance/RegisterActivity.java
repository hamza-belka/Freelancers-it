package com.hamzabelkhiria.miniprojectfreelance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Intent;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hamzabelkhiria.miniprojectfreelance.Employer.PostEmpInfoProfile;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Registereducation;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "usermail";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";
    private static final String KEY_ROLE = "role";
    private static final String KEY_USERID = "id";
    private static final String KEY_IMAGE = "image";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etFullName;
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private ProgressDialog pDialog;
    public Generaladress g= new Generaladress();
    private String register_url =g.URLfirstpart+"/register.php";
    private SessionHandler session;
    private String valuechoix;
    private ImageView profileimage;
    private Button uploadimg;
    private final int IMAGE_REQUEST=1;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        setContentView(R.layout.activity_register);
        Intent i = getIntent();
        valuechoix=i.getStringExtra("role");
        Log.d("role",valuechoix);

        etUsername = findViewById(R.id.etmail);
        etPassword = findViewById(R.id.etpassword);
        etConfirmPassword = findViewById(R.id.etconfirmpassword);
        etFullName = findViewById(R.id.etfullname);
        profileimage = findViewById(R.id.signupimg);
        uploadimg = findViewById(R.id.uploadimg);
        Button login = findViewById(R.id.btnregisterlogin);
        Button register = findViewById(R.id.btnregister);
        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimage();
            }
        });
        //Launch Login screen when Login Button is clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                fullName = etFullName.getText().toString().trim();
                Log.d("image",imagetoString(bitmap));
                if (validateInputs()) {
                    registerUser();
                }

            }
        });

    }

    /**
     * Display Progress bar while registering
     */
    private void displayLoader() {
        pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setMessage("Signing Up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    /**
     * Launch Dashboard Activity on Successful Sign Up
     */
    private void loadDashboard() {
        Intent i = new Intent();
        if(valuechoix.equals("freelancer")) {
            i = new Intent(getApplicationContext(), Registereducation.class);
        }else{
            i = new Intent(getApplicationContext(), PostEmpInfoProfile.class);
        }
            startActivity(i);
        finish();

    }

    private void registerUser() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);
            request.put(KEY_FULL_NAME, fullName);
            request.put(KEY_ROLE, valuechoix.trim());
            request.put("token", FirebaseInstanceId.getInstance().getToken());
            request.put(KEY_IMAGE,imagetoString(bitmap));


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dfgh",e.getMessage());
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();


                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                String imagepath = response.getString("image");
                                int userid = response.getInt(KEY_USERID);
                                session.loginUser(username,fullName,valuechoix,userid,imagepath);
                                loadDashboard();

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing
                                etUsername.setError("Username already taken!");
                                etUsername.requestFocus();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("dfgh54", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Log.d("dfgh100", "["+error.getLocalizedMessage()+"]");
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
        if (KEY_EMPTY.equals(fullName)) {
            etFullName.setError("Full Name cannot be empty");
            etFullName.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(username)) {

            etUsername.setError("Email cannot be empty");
            etUsername.requestFocus();
            return false;

        }
        if(!etUsername.getText().toString().contains("@")){
            etUsername.setError("E-mail non valide");
            etUsername.requestFocus();
            return false;

        }

        if (KEY_EMPTY.equals(password)) {
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }



        if (KEY_EMPTY.equals(confirmPassword)) {
            etConfirmPassword.setError("Confirm Password cannot be empty");
            etConfirmPassword.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password and Confirm Password does not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }
    private void selectimage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                profileimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }



    }
private void uploadimage(){


}
private String imagetoString(Bitmap bitmap){
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
    byte[] imgBytes = byteArrayOutputStream.toByteArray();
    return Base64.encodeToString(imgBytes,Base64.DEFAULT);

}





}