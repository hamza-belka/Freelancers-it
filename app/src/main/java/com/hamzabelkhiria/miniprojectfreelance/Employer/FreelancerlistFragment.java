package com.hamzabelkhiria.miniprojectfreelance.Employer;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Adapters.FreelancerAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.Models.ProfileInfos;
import com.hamzabelkhiria.miniprojectfreelance.Models.Skill;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;
import com.hamzabelkhiria.miniprojectfreelance.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreelancerlistFragment extends Fragment {
    RecyclerView freelancerrecycler;
    ArrayList<ProfileInfos> profilelist = new ArrayList<ProfileInfos>();
    ArrayList<Skill> skilllist = new ArrayList<Skill>();
    ArrayList<User> userlist = new ArrayList<User>();
    View view;
    Generaladress g;
    SessionHandler session;
    public FreelancerlistFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        session = new SessionHandler(getContext());
        g= new Generaladress();
        String UrlListjob=g.URLfirstpart+"/employerfreelancer/listfreelancer.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,UrlListjob,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray freelancers =  response.getJSONArray("freelancers");
                            Log.d("array", freelancers.toString());


                            Log.d("array", "{"+String.valueOf(response)+"}");
                            for (int i = 0; i < freelancers.length(); i++) {
                                JSONObject c = freelancers.getJSONObject(i);
                                // Log.d("fgfg", String.valueOf(edulist.length()));
                                int id = c.getInt("userid");
                                String usermail = c.getString("usermail");
                                String fullname = c.getString("fullname");
                                String profiletitle = c.getString("profiletitle");
                                String location = c.getString("location");
                                String about = c.getString("about");
                                String skill = c.getString("skill");
                                User u=new User();
                                u.setUserid(id);
                                u.setUsername(usermail);
                                u.setFullName(fullname);
                                userlist.add(u);
                                ProfileInfos p = new ProfileInfos();
                                p.setProfiletitle(profiletitle);
                                p.setAbout(about);
                                p.setLocation(location);
                                profilelist.add(p);
                                Skill s= new Skill();
                                s.setSkill_description(skill);
                                skilllist.add(s);








                            }
                            FreelancerAdapter freelancerAdapter= new FreelancerAdapter(getActivity(),profilelist,userlist,skilllist,false);
                            freelancerrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                            freelancerrecycler.setAdapter(freelancerAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("error",error.getMessage());
            }
        });

        Volley.newRequestQueue(getContext()).add(request);

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_empfreelancerlist, container, false);
        freelancerrecycler=view.findViewById(R.id.freelancerlist);



        return view;
    }

}
