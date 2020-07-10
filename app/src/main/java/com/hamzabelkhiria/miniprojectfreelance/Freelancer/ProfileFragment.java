package com.hamzabelkhiria.miniprojectfreelance.Freelancer;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Adapters.EduAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Adapters.ExpAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Getprofile;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.Models.Education;
import com.hamzabelkhiria.miniprojectfreelance.Models.Experience;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
     Generaladress f;

    String UrlSkills;
    String url_edu;
    String url_exp;
    String Urlprofileinfos;



    private RequestQueue mQueue;
    View view;
    ImageView profileimg;
    Generaladress g= new Generaladress();
    private SharedPreferences mPreferences;
    Getprofile profile;
    private SessionHandler session;
    private RecyclerView educationlist;
    private RecyclerView experiencelist;
    List<Education> edus;
    List<Experience> exps;
    TextView skill,profiletitle,adress,abt,fullname;

    RequestQueue rq ;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f= new Generaladress();
        UrlSkills=f.URLfirstpart+"/profiles/getskill.php";
        url_edu = f.URLfirstpart+"/profiles/listeducation.php";
        url_exp = f.URLfirstpart+"/profiles/listexperience.php";
        Urlprofileinfos=f.URLfirstpart+"/profiles/getprofileinfos.php";
        session = new SessionHandler(getContext());
        //profile=new Getprofile(getContext(),session.getUserDetails().getUserid());
        edus = new ArrayList<Education>();
        exps = new ArrayList<Experience>();
        //edus=profile.listeducation();
        // Inflate the layout for this fragment

        mQueue = Volley.newRequestQueue(getContext());

        geteducations();
        getexperiences();
        getskill();
        getprofileinfos();

    }

    private void geteducations() {
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url_edu,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray edulist =  response.getJSONArray("edulist");
                            Log.d("array", edulist.toString());


                            Log.d("array", "{"+String.valueOf(response)+"}");
                           for (int i = 0; i < edulist.length(); i++) {
                                JSONObject c = edulist.getJSONObject(i);
                                Log.d("fgfg", String.valueOf(edulist.length()));
                                int id = c.getInt("id");
                                Log.d("cvgbn", String.valueOf(id));
                                String school = c.getString("school");
                                String degree = c.getString("degree");
                                String startstudydate = c.getString("startstudydate");
                                String endstudydate = c.getString("endstudydate");
                                Education edu = new Education(school,degree,startstudydate,endstudydate);
                                edu.setId(id);
                                edus.add(edu);


                            }
                            EduAdapter eduAdapter = new EduAdapter(getActivity().getApplicationContext(),edus);
                            educationlist.setLayoutManager(new LinearLayoutManager(getActivity()));
                            educationlist.setAdapter(eduAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Log.d("error",error.getMessage());
            }
        });

        mQueue.add(request);
    }

    private void getskill() {
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, UrlSkills,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject edulist =  response.getJSONObject("skill");
                            Log.d("array", edulist.toString());


                            Log.d("array", "{"+String.valueOf(response)+"}");



                                int id = edulist.getInt("id");
                                String skills = edulist.getString("skill_description");
                                skill.setText(skills);



                            } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mQueue.add(request);



    }
    private void getexperiences() {
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url_exp,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray edulist =  response.getJSONArray("exps");
                            Log.d("array", edulist.toString());


                            Log.d("array", "{"+String.valueOf(response)+"}");
                            for (int i = 0; i < edulist.length(); i++) {
                                JSONObject c = edulist.getJSONObject(i);
                                Log.d("fgfg", String.valueOf(edulist.length()));
                                int id = c.getInt("id");
                                Log.d("cvgbn", String.valueOf(id));
                                String exptitle = c.getString("exptitle");
                                String expcompany = c.getString("expcompany");
                                String startstudydate = c.getString("startdate");
                                String endstudydate = c.getString("enddate");
                                Experience exp = new Experience(exptitle,expcompany,startstudydate,endstudydate);
                                exp.setId(id);
                                exps.add(exp);


                            }
                            ExpAdapter expAdapter = new ExpAdapter(getContext(),exps);
                            experiencelist.setLayoutManager(new LinearLayoutManager(getActivity()));
                            experiencelist.setAdapter(expAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Log.d("error",error.getMessage());
            }
        });

        mQueue.add(request);



    }
    private void getprofileinfos() {
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Urlprofileinfos,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject edulist =  response.getJSONObject("profile");
                            Log.d("array", edulist.toString());


                            Log.d("array", "{"+String.valueOf(response)+"}");



                            int id = edulist.getInt("id");
                            String title = edulist.getString("profiletitle");
                            String about = edulist.getString("about");
                            String location = edulist.getString("location");


                            profiletitle.setText(title);
                            abt.setText(about);
                            adress.setText(location);




                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Log.d("error",error.getMessage());
            }
        });

        mQueue.add(request);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile, container, false);


        //Log.d("parsing result", ""+profile.listeducation()+"");
        educationlist= (RecyclerView) view.findViewById(R.id.educationlist);
        experiencelist= (RecyclerView) view.findViewById(R.id.experiencelist);

        profileimg=view.findViewById(R.id.profileimage);
        TextView txt=view.findViewById(R.id.abt);
         skill=view.findViewById(R.id.skilldescription);
         profiletitle=view.findViewById(R.id.profiletitle);
         abt = view.findViewById(R.id.abt);
         adress=view.findViewById(R.id.location);
         fullname=view.findViewById(R.id.nomprenom);
         fullname.setText(session.getUserDetails().getFullName());







        mPreferences = getContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String usermail = mPreferences.getString("username","error");
        String imagepath= g.URLfirstpart+"/"+ mPreferences.getString("image","uploads/"+usermail+".jpeg");
        new GetImageFromURL(profileimg).execute(imagepath);



        return view;}

}
