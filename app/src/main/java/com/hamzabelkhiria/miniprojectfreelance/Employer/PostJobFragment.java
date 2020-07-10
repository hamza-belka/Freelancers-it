package com.hamzabelkhiria.miniprojectfreelance.Employer;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.MySingleton;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostJobFragment extends Fragment {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_titre = "title";
    private static final String KEY_DESC = "jobdescription";
    private static final String KEY_skills = "skills";
    private static final String KEY_sallary = "budget";
    private static final String KEY_typebudget = "budgettype";
    private static final String KEY_EMPTY = "";
    private static final String Userid = "posterid";
    private static final String KEY_mailposter = "postermail";
    public Generaladress g= new Generaladress();
    private String addjob_url =g.URLfirstpart+"/jobs/addjob.php";
    private String titleproject;
    private String descreption;
    private String budgettype;
    private String budgett;
    private String skillss;
    TextView titre;
    TextView desc;
    TextView salary;
    TextView skills;
    RadioGroup rg;
    private SessionHandler session;
    private ProgressDialog pDialog;


    RadioButton fixed, hourly;
    View view;


    public PostJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new SessionHandler(getActivity());
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_empsettings, container, false);

        titre= view.findViewById(R.id.jobtitle);
       desc= view.findViewById(R.id.jobdesc);
         salary= view.findViewById(R.id.jobsalary);
         skills= view.findViewById(R.id.jobskills);
         rg=view.findViewById(R.id.radioGroup);
        Button post=view.findViewById(R.id.btnpostproject);
        //int i=rg.getCheckedRadioButtonId();


       /* rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                fixed = (RadioButton) view.findViewById(i);

            }
        });*/


       post.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               titleproject = titre.getText().toString().toLowerCase();
               descreption = desc.getText().toString();
               skillss = skills.getText().toString();
               budgett = salary.getText().toString().trim();
               int radioId = rg.getCheckedRadioButtonId();
               fixed =(RadioButton) view.findViewById(radioId);
               budgettype = fixed.getText().toString().toLowerCase().trim();
               if (validateInputs()) {
                   registerUser();
               }



           }
       });

                return view;
    }
    private void registerUser() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_titre, titleproject);
            request.put(KEY_DESC, descreption);
            request.put(KEY_skills, skillss);
            request.put(KEY_typebudget, budgettype.trim());
            request.put(KEY_sallary,budgett);
            request.put(Userid, session.getUserDetails().getUserid());
            request.put(KEY_mailposter,session.getUserDetails().getUsername());


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dfgh",e.getMessage());
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, addjob_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();


                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session


                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing

                            }else{
                                Toast.makeText(getActivity(),
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
                        Toast.makeText(getActivity(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsArrayRequest);
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("adding job post.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    private boolean validateInputs() {
        if (KEY_EMPTY.equals(titleproject)) {
            titre.setError("title  cannot be empty");
            titre.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(descreption)) {

            desc.setError("desc cannot be empty");
            desc.requestFocus();
            return false;

        }

        if (KEY_EMPTY.equals(skillss)) {
            skills.setError("skills cannot be empty");
            skills.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(budgett)) {
            salary.setError("skills cannot be empty");
            salary.requestFocus();
            return false;
        }





        return true;
    }

}
