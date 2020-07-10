package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.MySingleton;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class Submittodb {
    private int userid;
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private SessionHandler session;
    public String URL_ADD_EDU;
    public String URL_ADD_EXP;
    public String URL_ADD_INFO;
    public String URL_ADD_SKILL;
    public Submittodb(Context context) {
        Generaladress g = new Generaladress();
        session = new SessionHandler(context);
        userid = session.getUserDetails().getUserid();
        URL_ADD_EDU = g.URLfirstpart+"/profiles/addeducation.php";
        URL_ADD_EXP = g.URLfirstpart+"/profiles/addexperience.php";
        URL_ADD_INFO = g.URLfirstpart+"/profiles/addprofileinfos.php";
        URL_ADD_SKILL = g.URLfirstpart+"/profiles/addskill.php";

    }
    public void submitedu(final Context context, String school, String degree, String startstudydate, String endstudydate){

        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
           request.put("school", school);
            request.put("degree", degree);
            request.put("startstudydate", startstudydate);
            request.put("endstudydate", endstudydate);
            request.put("userid",userid);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dfgh1",e.getMessage());
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, URL_ADD_EDU, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                Log.d("result edu:",response.getString(KEY_MESSAGE));

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing

                            }else{
                                Toast.makeText(context,
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("11", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("error edu:", "["+error.getLocalizedMessage()+"]");
                        //Display error message whenever an error occurs
                        Toast.makeText(context,
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsArrayRequest);
    }
    public void submitexp(final Context context, String exptitle, String expcompany, String startdate, String enddate){

        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("exptitle", exptitle);
            request.put("expcompany", expcompany);
            request.put("startdate", startdate);
            request.put("enddate", enddate);
            request.put("userid",userid);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dfgh2",e.getMessage());
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, URL_ADD_EXP, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                Log.d("result exp:",response.getString(KEY_MESSAGE));

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing

                            }else{
                                Toast.makeText(context,
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("22", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("error exp:", "["+error.getLocalizedMessage()+"]");
                        //Display error message whenever an error occurs
                        Toast.makeText(context,
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsArrayRequest);
    }

    public void submitinfo(final Context context, String profiletitle, String about, String location){

        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("profiletitle", profiletitle);
            request.put("about", about);
            request.put("location", location);
            request.put("userid",userid);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dfgh3",e.getMessage());
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, URL_ADD_INFO, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                Log.d("result info:",response.getString(KEY_MESSAGE));

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing

                            }else{
                                Toast.makeText(context,
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("33", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("error info:", "["+error.getLocalizedMessage()+"]");
                        //Display error message whenever an error occurs
                        Toast.makeText(context,
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsArrayRequest);
    }
    public void submitskill(final Context context, String skill_description){

        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("skill_description", skill_description);
            request.put("userid",userid);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dfgh4",e.getMessage());
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, URL_ADD_SKILL, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                Log.d("result skill:",response.getString(KEY_MESSAGE));

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing

                            }else{
                                Toast.makeText(context,
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("44", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("error skill:", "["+error.getLocalizedMessage()+"]");
                        //Display error message whenever an error occurs
                        Toast.makeText(context,
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsArrayRequest);
    }




}
