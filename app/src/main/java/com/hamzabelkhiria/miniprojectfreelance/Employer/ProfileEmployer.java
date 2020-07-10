package com.hamzabelkhiria.miniprojectfreelance.Employer;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Getprofile;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileEmployer extends Fragment {

    Generaladress f;

    String Urlprofileinfos;



    private RequestQueue mQueue;
    View view;
    ImageView profileimg;
    Generaladress g= new Generaladress();
    private SharedPreferences mPreferences;
    Getprofile profile;
    private SessionHandler session;


    TextView profiletitle,adress,abt,fullname;

    RequestQueue rq ;
    public ProfileEmployer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f= new Generaladress();

        Urlprofileinfos=f.URLfirstpart+"/profiles/getprofileinfos.php";
        session = new SessionHandler(getContext());
        //profile=new Getprofile(getContext(),session.getUserDetails().getUserid());

        //edus=profile.listeducation();
        // Inflate the layout for this fragment

        mQueue = Volley.newRequestQueue(getContext());


        getprofileinfos();

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
                           // abt.setText(about);
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
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_profile_employer, container, false);

        profileimg=view.findViewById(R.id.profileimage);
       // TextView txt=view.findViewById(R.id.about);
        adress=view.findViewById(R.id.location);
        fullname=view.findViewById(R.id.nomprenom);
        profiletitle =view.findViewById(R.id.profiletitle);
        fullname.setText(session.getUserDetails().getFullName());







        mPreferences = getContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String usermail = mPreferences.getString("username","error");
        String imagepath= g.URLfirstpart+"/"+ mPreferences.getString("image","uploads/"+usermail+".jpeg");
        new GetImageFromURL(profileimg).execute(imagepath);

        return  view;
    }

}
