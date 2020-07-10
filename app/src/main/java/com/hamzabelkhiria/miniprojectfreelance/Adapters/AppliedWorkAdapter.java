package com.hamzabelkhiria.miniprojectfreelance.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Employer.Myapplications;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.DeatailsJob;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.Models.Work;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;
import com.hamzabelkhiria.miniprojectfreelance.ViewHolders.WorksViewholder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppliedWorkAdapter extends RecyclerView.Adapter<WorksViewholder> implements Filterable {
    Context mContext;
    List<Work> listworks;
    List<Work> filterlistworks;

    Generaladress g;
    boolean saved,employer,currentjobs;
    SessionHandler session;
    public AppliedWorkAdapter(Context mContext, List<Work> listworks, boolean saved, boolean employer, boolean currentjobs) {
        this.mContext = mContext;
        this.listworks = listworks;
        g = new Generaladress();
        this.saved =saved;
        this.employer=employer;
        filterlistworks= new ArrayList<>();
        this.currentjobs=currentjobs;
    }
        public void notifyChange(List<Work> listworks){
            this.listworks = listworks;
            this.notifyDataSetChanged();}
            @NonNull
    @Override
    public WorksViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singleworklayout,parent,false);
        WorksViewholder vh=new WorksViewholder(v);
        return  vh;


    }

    @Override
    public void onBindViewHolder(@NonNull WorksViewholder holder, int position) {
final Work currentitem=listworks.get(position);
        session = new SessionHandler(mContext);
holder.worktitle.setText(currentitem.getWorktitle());
holder.postermail.setText("Posted by "+currentitem.getFullname());
holder.workdescription.setText(currentitem.getDescription());
holder.budgettype.setText(String.valueOf(currentitem.getBudgettype()));
holder.budget.setText(String.valueOf(currentitem.getBudget()));
holder.jobstatus.setText(currentitem.getJobstatus());
        String imagepath= g.URLfirstpart+"/uploads/"+currentitem.getPostermail()+".jpeg";
        new GetImageFromURL(holder.posterimg).execute(imagepath);
        if (employer==true){
            holder.save.setVisibility(View.INVISIBLE);
            holder.apply.setVisibility(View.INVISIBLE);
            if (currentitem.getJobstatus().equals("looking for freelancers")){
            holder.job.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, Myapplications.class);
                    intent.putExtra("jobid",currentitem.getWorkid());
                    intent.putExtra("jobtitle",currentitem.getWorktitle());
                    mContext.startActivity(intent);
                }
            });

        }}else{
            holder.job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, DeatailsJob.class);
                intent.putExtra("jobid",currentitem.getWorkid());
                mContext.startActivity(intent);
            }
        });if (currentjobs== true) {holder.apply.setVisibility(View.INVISIBLE);}
                holder.apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        apply(currentitem.getWorkid(), session.getUserDetails().getUserid(), currentitem.getPosterid(), currentitem.getPostertoken(), currentitem.getWorktitle());
                        listworks.remove(currentitem);
                        notifyChange(listworks);
                    }
                });

        if (saved == true){
            holder.save.setVisibility(View.INVISIBLE);
        }else{
            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    session = new SessionHandler(mContext);
                    String Urlsavejob=g.URLfirstpart+"/jobs/savejob.php";
                    JSONObject rq = new JSONObject();
                    try {
                        //Populate the request parameters
                        rq.put("userid", session.getUserDetails().getUserid());
                        rq.put("jobid", currentitem.getWorkid());



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlsavejob,rq,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

                                               if (response.getInt("status") == 0) {
                                                   Toast.makeText(mContext,
                                                   response.getString("message"), Toast.LENGTH_SHORT).show();

                                                   listworks.remove(currentitem);
                                                   

                                                   notifyChange(listworks);


                                               }  else {

                                                        Toast.makeText(mContext,
                                                        "Error saving job, try again later ", Toast.LENGTH_SHORT).show();

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
   }

    private void apply(int workid, int userid, int posterid, String postertoken, String worktitle) {
        session = new SessionHandler(mContext);
        String Urlapply=g.URLfirstpart+"/jobs/apply.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("freelancerid", session.getUserDetails().getUserid());
            rq.put("jobid", workid);
            rq.put("employerid", posterid);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlapply,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("status") == 0) {
                                Toast.makeText(mContext,
                                        response.getString("message"), Toast.LENGTH_SHORT).show();




                            }  else {

                                Toast.makeText(mContext,
                                        "Error applying to  job, try again later ", Toast.LENGTH_SHORT).show();

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


        String Urlnotify=g.URLfirstpart+"/applynotif.php?send_notification";
        JSONObject rq1 = new JSONObject();
        try {
            //Populate the request parameters
            rq1.put("token", postertoken);
            rq1.put("textmsg", session.getUserDetails().getFullName()+" just applied for your job:"+worktitle);
            rq1.put("jobid", workid);
            rq1.put("jobtitle",worktitle);






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
                                        "Error applying to  job, try again later ", Toast.LENGTH_SHORT).show();

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






    @Override
    public int getItemCount() {
        return listworks.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Work> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterlistworks);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Work item : filterlistworks) {
                    if (item.getWorktitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listworks.clear();
            listworks.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
