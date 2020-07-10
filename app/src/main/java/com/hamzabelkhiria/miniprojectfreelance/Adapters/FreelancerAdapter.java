package com.hamzabelkhiria.miniprojectfreelance.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Employer.DashboardEmployer;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.Models.ProfileInfos;
import com.hamzabelkhiria.miniprojectfreelance.Models.Skill;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;
import com.hamzabelkhiria.miniprojectfreelance.User;
import com.hamzabelkhiria.miniprojectfreelance.ViewHolders.FreelancerViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FreelancerAdapter extends RecyclerView.Adapter<FreelancerViewHolder> {
    Context mContext;
    List<ProfileInfos> listprofileinfos;
    List<User> listusers;
    List<Skill> listskills;
    Generaladress g;
    Boolean saved;
    SessionHandler session;
    int jobid;
    String jobtitle;


    public FreelancerAdapter(Context mContext, List<ProfileInfos> listprofileinfos, List<User> listusers, List<Skill> listskills, Boolean saved) {
        this.mContext = mContext;
        this.listprofileinfos = listprofileinfos;
        this.listusers = listusers;
        this.listskills = listskills;
        g=new Generaladress();
        this.saved= saved;
    }
    public FreelancerAdapter(Context mContext, List<ProfileInfos> listprofileinfos, List<User> listusers, List<Skill> listskills, Boolean saved, int jobid,String jobtitle) {
        this.mContext = mContext;
        this.listprofileinfos = listprofileinfos;
        this.listusers = listusers;
        this.listskills = listskills;
        g=new Generaladress();
        this.saved= saved;
        this.jobid= jobid;
        this.jobtitle=jobtitle;
    }

    public void notifyChange(List<ProfileInfos> listprofileinfos, List<User> listusers, List<Skill> listskills){
        this.listprofileinfos = listprofileinfos;
        this.listusers = listusers;
        this.listskills = listskills;
        this.notifyDataSetChanged();}

    @NonNull
    @Override
    public FreelancerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlerowlistfreelancer,parent,false);
        FreelancerViewHolder vh=new FreelancerViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FreelancerViewHolder holder, final int position) {
        final ProfileInfos currentprofile= listprofileinfos.get(position);
        final User currentuser= listusers.get(position);
        final Skill currentskill= listskills.get(position);


        holder.nom.setText(currentuser.getFullName());
        holder.title.setText(currentprofile.getProfiletitle());
        holder.skillset.setText(currentskill.getSkill_description());
        holder.location.setText(currentprofile.getLocation());
        String imagepath= g.URLfirstpart+"/uploads/"+currentuser.getUsername()+".jpeg";
        new GetImageFromURL(holder.freelancerimage).execute(imagepath);
        holder.hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changejobstatus(jobid,currentuser.getFullName());
                inserthire(jobid,currentuser.getUserid());
                deletealljobapplications(jobid);
                notifyfreelancer(currentuser.getToken(),jobtitle, jobid);
                Intent intent= new Intent(mContext, DashboardEmployer.class);
                mContext.startActivity(intent);

            }
        });
        if (saved==true){
            holder.save.setVisibility(View.INVISIBLE);
        }else {
            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    session = new SessionHandler(mContext);

                    String Urlsavefreelancer=g.URLfirstpart+"/employerfreelancer/savefreelancer.php";
                    JSONObject rq = new JSONObject();
                    try {
                        //Populate the request parameters
                        rq.put("userid", session.getUserDetails().getUserid());
                        rq.put("freelancerid", currentuser.getUserid());



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlsavefreelancer,rq,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

                                        if (response.getInt("status") == 0) {
                                            Toast.makeText(mContext,
                                                    response.getString("message"), Toast.LENGTH_SHORT).show();

                                            listusers.remove(currentuser);
                                            listprofileinfos.remove(currentprofile);
                                            listskills.remove(currentskill);


                                            notifyChange(listprofileinfos,listusers,listskills);


                                        }  else {

                                            Toast.makeText(mContext,
                                                    "Error saving freelancer, try again later ", Toast.LENGTH_SHORT).show();

                                        }










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

                    Volley.newRequestQueue(mContext).add(request);







                }
            });

                }



        }

    private void notifyfreelancer(String token, String jobtitle, int jobid) {
        String Urlnotify=g.URLfirstpart+"/hirenotif.php?send_notification";
        JSONObject rq1 = new JSONObject();
        try {
            //Populate the request parameters
            rq1.put("token", token);
            rq1.put("textmsg", session.getUserDetails().getFullName()+" just accpeted your application for:"+jobtitle);
            rq1.put("jobid", jobid);
            rq1.put("jobtitle",jobtitle);






        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST,Urlnotify,rq1,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("response result",response.toString());
                            if (response.getInt("status") == 0) {
                                Toast.makeText(mContext,
                                        response.getString("message"), Toast.LENGTH_SHORT).show();





                            }  else {

                                Toast.makeText(mContext,
                                        "Error notifying freelancer, try again later ", Toast.LENGTH_SHORT).show();

                            }










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

        Volley.newRequestQueue(mContext).add(request1);







    }

    private void deletealljobapplications(int jobid) {
        session = new SessionHandler(mContext);

        String Urldeleteapplications=g.URLfirstpart+"/employerfreelancer/deleteallapplications.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("jobid", jobid);





        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urldeleteapplications,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("status") == 0) {
                                Toast.makeText(mContext,
                                        response.getString("message"), Toast.LENGTH_SHORT).show();





                            }  else {

                                Toast.makeText(mContext,
                                        "Error deleting other job applications, try again later ", Toast.LENGTH_SHORT).show();

                            }










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

        Volley.newRequestQueue(mContext).add(request);


    }

    private void inserthire(int jobid, int userid) {
        session = new SessionHandler(mContext);

        String Urlhire=g.URLfirstpart+"/employerfreelancer/hire.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("jobid", jobid);
            rq.put("freelancerid", userid);
            rq.put("employerid", session.getUserDetails().getUserid());




        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlhire,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("status") == 0) {
                                Toast.makeText(mContext,
                                        response.getString("message"), Toast.LENGTH_SHORT).show();





                            }  else {

                                Toast.makeText(mContext,
                                        "Error hiring freelancer, try again later ", Toast.LENGTH_SHORT).show();

                            }










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

        Volley.newRequestQueue(mContext).add(request);


    }

    private void changejobstatus(int jobid, String fullname) {
        session = new SessionHandler(mContext);

        String Urlchangejobstatus=g.URLfirstpart+"/jobs/changejobstatus.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("jobid", jobid);
            rq.put("freelancername", "job in progress(assigned to "+fullname+")");




        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlchangejobstatus,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("status") == 0) {
                                Toast.makeText(mContext,
                                        response.getString("message"), Toast.LENGTH_SHORT).show();





                            }  else {

                                Toast.makeText(mContext,
                                        "Error changing job status, try again later ", Toast.LENGTH_SHORT).show();

                            }










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

        Volley.newRequestQueue(mContext).add(request);



    }


    @Override
    public int getItemCount() {
        return listprofileinfos.size();
    }
}
