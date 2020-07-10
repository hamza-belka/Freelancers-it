package com.hamzabelkhiria.miniprojectfreelance.Freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeatailsJob extends AppCompatActivity {
     private TextView postedby,profiletitle,datepost,jobtitle,description,budgettype,budget,skills;
     private Button apply, jobdone;
     private CircleImageView posterimage;
    Generaladress g;
    SessionHandler session;
    Dialog myDialog;
    int jobid,posterid;
    String postertoken, worktitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails_job);
        session = new SessionHandler(getApplicationContext());

        Intent intent = getIntent();
        int id = intent.getIntExtra("jobid",0);
        String current = intent.getStringExtra("current");


        postedby=findViewById(R.id.detailspostedby);
        profiletitle=findViewById(R.id.detailsprofiletitle);
        datepost=findViewById(R.id.detailsdatepost);
        jobtitle=findViewById(R.id.detailsjobtitle);
        description=findViewById(R.id.detailsjobdescription);
        budgettype=findViewById(R.id.detailsbudgettype);
        budget=findViewById(R.id.detailsbudget);
        skills=findViewById(R.id.detailsskills);
        apply=findViewById(R.id.detailsapply);
        posterimage=findViewById(R.id.detailsposterimg);
        jobdone=findViewById(R.id.jobdone);
        jobdone.setVisibility(View.INVISIBLE);

        getjobdetails(id);
        if (current!=null) {
            jobdone.setVisibility(View.VISIBLE);
            apply.setVisibility(View.INVISIBLE);

        }
         apply.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 session = new SessionHandler(getApplicationContext());
                 String Urlapply=g.URLfirstpart+"/jobs/apply.php";
                 JSONObject rq = new JSONObject();
                 try {
                     //Populate the request parameters
                     rq.put("freelancerid", session.getUserDetails().getUserid());
                     rq.put("jobid", jobid);
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
                                         Toast.makeText(getApplicationContext(),
                                                 response.getString("message"), Toast.LENGTH_SHORT).show();




                                     }  else {

                                         Toast.makeText(getApplicationContext(),
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

                 Volley.newRequestQueue(getApplicationContext()).add(request);


                 String Urlnotify=g.URLfirstpart+"/applynotif.php?send_notification";
                 JSONObject rq1 = new JSONObject();
                 try {
                     //Populate the request parameters
                     rq1.put("token", postertoken);
                     rq1.put("textmsg", session.getUserDetails().getFullName()+" just applied for your job:"+worktitle);
                     rq1.put("jobid", jobid);
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
                                         Toast.makeText(getApplicationContext(),
                                                 response.getString("message"), Toast.LENGTH_SHORT).show();




                                     }  else {

                                         Toast.makeText(getApplicationContext(),
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

                 Volley.newRequestQueue(getApplicationContext()).add(request1);



Intent intent = new Intent(getApplicationContext(),DashboardFreelancer.class);
startActivity(intent);



             }
         });
        jobdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             showpopup();

            }
        });



    }

    private void showpopup() {
        myDialog = new Dialog(this);
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        Button submit = (Button) myDialog.findViewById(R.id.submit);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Urlchangejobstatus=g.URLfirstpart+"/jobs/changejobstatus.php";
                JSONObject rq = new JSONObject();
                try {
                    //Populate the request parameters
                    rq.put("jobid", jobid);
                    rq.put("freelancername", "job awaiting termination (assigned to "+session.getUserDetails().getFullName()+")");




                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlchangejobstatus,rq,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    if (response.getInt("status") == 0) {
                                        Toast.makeText(getApplicationContext(),
                                                response.getString("message"), Toast.LENGTH_SHORT).show();





                                    }  else {

                                        Toast.makeText(getApplicationContext(),
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

                Volley.newRequestQueue(getApplicationContext()).add(request);

                String Urlnotify=g.URLfirstpart+"/completnotif.php?send_notification";
                JSONObject rq1 = new JSONObject();
                try {
                    //Populate the request parameters
                    rq1.put("token", postertoken);
                    rq1.put("textmsg", session.getUserDetails().getFullName()+" just declared completion for:"+worktitle);






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
                                        Toast.makeText(getApplicationContext(),
                                                response.getString("message"), Toast.LENGTH_SHORT).show();





                                    }  else {

                                        Toast.makeText(getApplicationContext(),
                                                "Error notifying employer, try again later ", Toast.LENGTH_SHORT).show();

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

                Volley.newRequestQueue(getApplicationContext()).add(request1);

                myDialog.dismiss();
                Intent intent= new Intent(getApplicationContext(), DashboardFreelancer.class);
                startActivity(intent);







            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();


    }

    private void getjobdetails(int id) {
        g= new Generaladress();
        String UrlListjob=g.URLfirstpart+"/jobs/jobdetails.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("jobid", id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,UrlListjob,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject c =  response.getJSONObject("job");
                            Log.d("jobdetails", c.toString());



                                // Log.d("fgfg", String.valueOf(edulist.length()));
                                int id = c.getInt("jobid");
                                jobid=id;
                                Log.d("jobid", String.valueOf(id));
                                String title1 = c.getString("title");
                                String description1 = c.getString("description");
                                String datepost1 = c.getString("datepost");
                                String budgettype1 = c.getString("budgettype");

                                String budget1 = c.getString("budget");
                                String skills1 = c.getString("skills");
                                String postermail1 = c.getString("postermail");
                                int  posterid1 = c.getInt("posterid");
                                String full_name1 = c.getString("full_name");
                                String userimage1=c.getString("userimage");
                                String profiletitle1=c.getString("profiletitle");
                                description.setText(description1);
                                profiletitle.setText(profiletitle1);
                                String jobstatus = c.getString("jobstatus");
                                if (!jobstatus.equals("looking for freelancers")){
                                    apply.setVisibility(View.INVISIBLE);
                                }
                                datepost.setText(datepost1);
                                budgettype.setText(budgettype1);
                                budget.setText(budget1);
                                skills.setText(skills1);
                                postedby.setText(full_name1);
                                jobtitle.setText(title1);
                                worktitle=title1;
                                postertoken=c.getString("token");
                                posterid=posterid1;
                            String imagepath= g.URLfirstpart+userimage1;
                            new GetImageFromURL(posterimage).execute(imagepath);






                            } catch (JSONException ex) {
                            ex.printStackTrace();
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
