package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Subfragments;


import android.os.Bundle;

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
import com.hamzabelkhiria.miniprojectfreelance.Adapters.WorkAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.Models.Work;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppliedJobsFreelancer extends Fragment {
   private RecyclerView appliedrecycler;
   View view;
    WorkAdapter workAdapter;
    Work.budgettypes  budtype;
    Generaladress g;
    SessionHandler session;
    ArrayList<Work> worklist;
    boolean loaded = false;
    public AppliedJobsFreelancer() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_applied_jobs_freelancer, container, false);
        appliedrecycler=view.findViewById(R.id.appliedjobsrecycler);
        worklist= new ArrayList<Work>();

        session = new SessionHandler(getContext());
        g= new Generaladress();
        String Urlappliedjobs=g.URLfirstpart+"/jobs/appliedjobs.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlappliedjobs,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray joblist =  response.getJSONArray("joblist");
                            Log.d("array saved", joblist.toString());

                            Log.d("array saved", "{"+String.valueOf(response)+"}");
                            for (int i = 0; i < joblist.length(); i++) {
                                JSONObject c = joblist.getJSONObject(i);
                                // Log.d("fgfg", String.valueOf(edulist.length()));
                                int id = c.getInt("jobid");
                                Log.d("cvgbn", String.valueOf(id));
                                String title = c.getString("title");
                                String description = c.getString("description");
                                String datepost = c.getString("datepost");
                                String budgettype = c.getString("budgettype");
                                if (budgettype.equals("hourly")){
                                    budtype=Work.budgettypes.hourly;
                                }else{
                                    budtype=Work.budgettypes.fixed;

                                }
                                String budget = c.getString("budget");
                                String skills = c.getString("skills");
                                String postermail = c.getString("postermail");
                                int  posterid = c.getInt("posterid");
                                String full_name = c.getString("full_name");
                                String token = c.getString("token");
                                String jobstatus= c.getString("jobstatus");


                                Work job = new Work(postermail,posterid,description,title,budtype,budget,full_name,skills);
                                job.setWorkid(id);
                                job.setPostertoken(token);
                                job.setJobstatus(jobstatus);

                                worklist.add(job);




                            }

                            WorkAdapter workAdapter= new WorkAdapter(getActivity(),worklist,true,false,false);
                            appliedrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                            appliedrecycler.setAdapter(workAdapter);
                            loaded=true;
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







        return view;
    }

}
