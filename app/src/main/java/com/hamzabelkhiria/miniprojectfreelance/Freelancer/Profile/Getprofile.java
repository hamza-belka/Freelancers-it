package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.Models.Education;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Getprofile {
    private int userid;
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private SessionHandler session;
    public String URL_GET_EDU;
    public String URL_GET_EXP;
    public String URL_GET_INFO;
    public String URL_GET_SKILL;
    Context context;
    ArrayList<Education> listedu;
    public Getprofile(Context context) {
        Generaladress g = new Generaladress();
        this.context=context;
        session = new SessionHandler(context);
        userid = session.getUserDetails().getUserid();
        URL_GET_EDU=g.URLfirstpart+"/profiles/listeducation.php";
        URL_GET_EXP=g.URLfirstpart+"/profiles/listexperience.php";
        URL_GET_INFO=g.URLfirstpart+"/profiles/getprofileinfos.php";
        URL_GET_SKILL=g.URLfirstpart+"/profiles/getskill.php";

    }

    public ArrayList<Education> listeducation(){
        listedu = new ArrayList<Education>();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("userid", 55);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, URL_GET_EDU, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray educations = response.getJSONArray("edulist");

                            // looping through All Contacts
                            for (int i = 0; i < educations.length(); i++) {
                                JSONObject c = educations.getJSONObject(i);
                                int id = c.getInt("id");
                                String school = c.getString("school");
                                String degree = c.getString("degree");
                                String startstudydate = c.getString("startstudydate");
                                String endstudydate = c.getString("endstudydate");
                                Education edu = new Education(school,degree,startstudydate,endstudydate);
                                edu.setId(id);
                                listedu.add(edu);
                                // Phone node is JSON Object


                                // tmp hash map for single contact

                                //Check if user got logged in successfully


                            } } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        // Access the RequestQueue through your singleton class.
        Volley.newRequestQueue(context).add(jsArrayRequest);







    return listedu;}
}
