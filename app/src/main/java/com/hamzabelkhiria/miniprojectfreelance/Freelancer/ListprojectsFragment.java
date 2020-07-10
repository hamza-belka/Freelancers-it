package com.hamzabelkhiria.miniprojectfreelance.Freelancer;


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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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
public class ListprojectsFragment extends Fragment {
    RecyclerView workslistrec;
    private RequestQueue mQueue;
    Work.budgettypes  budtype;
    private SharedPreferences mPreferences;
    ArrayList<Work> worklist = new ArrayList<Work>();
    Generaladress g;
    SessionHandler session;
View view;
    public ListprojectsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        session = new SessionHandler(getContext());
        g= new Generaladress();
        String UrlListjob=g.URLfirstpart+"/jobs/listjobs.php";
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

                            JSONArray joblist =  response.getJSONArray("joblist");
                            Log.d("array", joblist.toString());


                            Log.d("array", "{"+String.valueOf(response)+"}");
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
                                Work job = new Work(postermail,posterid,description,title,budtype,budget,full_name,skills);
                                job.setWorkid(id);
                                worklist.add(job);




                            }
                            WorkAdapter workAdapter= new WorkAdapter(getActivity(),worklist,false,false,false);
                            workslistrec.setLayoutManager(new LinearLayoutManager(getActivity()));
                            workslistrec.setAdapter(workAdapter);
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
        view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_worklist, container, false);

        workslistrec= view.findViewById(R.id.worklist);



        return  view;
    }



}
