package com.hamzabelkhiria.miniprojectfreelance.Employer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class Myapplications extends AppCompatActivity {
    Generaladress g;
    SessionHandler session;
    private TextView appliedjobtitle;
    private RecyclerView appliedfreelancers;
    ArrayList<ProfileInfos> profilelist = new ArrayList<ProfileInfos>();
    ArrayList<Skill> skilllist = new ArrayList<Skill>();
    ArrayList<User> userlist = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myapplications);
        session = new SessionHandler(getApplicationContext());
        appliedfreelancers=findViewById(R.id.appliedfreerecyclerView);
        Intent intent = getIntent();
        int id = intent.getIntExtra("jobid",0);
        String jobtitle = intent.getStringExtra("jobtitle");
        appliedjobtitle=findViewById(R.id.appliedjobtitle);
        appliedjobtitle.setText(jobtitle);
        getlistappliedfreelancers(id,jobtitle);
        
        
        
        
        




    }

    private void getlistappliedfreelancers(final int id, final String jobtitle) {
        g= new Generaladress();

        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());
            rq.put("jobid", id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String UrlListjob=g.URLfirstpart+"/employerfreelancer/myapplicationsemp.php";

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
                                String token = c.getString("token");
                                User u=new User();
                                u.setUserid(id);
                                u.setUsername(usermail);
                                u.setFullName(fullname);
                                u.setToken(token);
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
                            FreelancerAdapter freelancerAdapter= new FreelancerAdapter(getApplicationContext(),profilelist,userlist,skilllist,true,id,jobtitle);
                            appliedfreelancers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            appliedfreelancers.setAdapter(freelancerAdapter);
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

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }
}
