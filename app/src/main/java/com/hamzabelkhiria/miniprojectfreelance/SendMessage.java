package com.hamzabelkhiria.miniprojectfreelance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Adapters.ContactAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Employer.DashboardEmployer;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.DashboardFreelancer;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SendMessage extends AppCompatActivity {
    ContactAdapter contactAdapter;
    Generaladress g;
    SessionHandler session;
    ArrayList<User> listcontact;
    ArrayList<User> list;
    Spinner contactspinner;
    Button send;
    Dialog myDialog;
    int receiverid, userid;
    String receivertoken, title, usertoken, object, body;
    EditText msgobject, msgbody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        session = new SessionHandler(getApplicationContext());
        userid=session.getUserDetails().getUserid();
        usertoken= FirebaseInstanceId.getInstance().getToken();
        title=session.getUserDetails().getFullName()+"have sent you a message";
        msgobject=findViewById(R.id.msgobject);
        msgbody=findViewById(R.id.postmessagebody);
        send= findViewById(R.id.send);
        g= new Generaladress();
        listcontact= new ArrayList<User>();

        if (session.getUserDetails().getRole().equals("freelancer")){

            getlistemployers();

        }else{getlistfreelancers();}

        contactspinner=findViewById(R.id.spinner);





        contactspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contactAdapter.notifyDataSetChanged();


                User ClikedItem=(User) parent.getItemAtPosition(position);

                receiverid=ClikedItem.getUserid();
                receivertoken=ClikedItem.getToken();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"hello1",Toast.LENGTH_SHORT).show();


            }
        });
        send.setOnClickListener(new View.OnClickListener() {
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
        myDialog.setContentView(R.layout.custompopupmessage);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
         object = msgobject.getText().toString();
         body = msgbody.getText().toString();
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
                Log.d("receivertoken",receivertoken);
                Log.d("sendertoken",usertoken);

                Log.d("receiverid", String.valueOf(receiverid));
                Log.d("title",title);
                Log.d("object",msgobject.getText().toString());
                Log.d("body",msgbody.getText().toString());
                Log.d("userid", String.valueOf(session.getUserDetails().getUserid()));


                String Urlsendmessage=g.URLfirstpart+"/messages/addmessage.php";

                JSONObject rq = new JSONObject();
                try {
                    //Populate the request parameters
                    rq.put("senderid",userid);
                    rq.put("receiverid",receiverid);
                    rq.put("sendertoken",usertoken);
                    rq.put("receivertoken",receivertoken);
                    rq.put("title",title);
                    rq.put("object",object);
                    rq.put("messagebody",body);











                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlsendmessage,rq,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    if (response.getInt("status") == 0) {
                                        Toast.makeText(getApplicationContext(),
                                                response.getString("message"), Toast.LENGTH_SHORT).show();





                                    }  else {

                                        Toast.makeText(getApplicationContext(),
                                                "Error submitting message, try again later ", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(),
                                                response.getString("message"), Toast.LENGTH_SHORT).show();

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

                String Urlnotifymessage=g.URLfirstpart+"/sendmessagenotification.php?send_notification";
                JSONObject rq1 = new JSONObject();
                try {
                    //Populate the request parameters
                    rq1.put("token", receivertoken);
                    rq1.put("textmsg", body);
                    rq1.put("title", title);
                    rq1.put("type", "message");
                    rq1.put("sendertoken", FirebaseInstanceId.getInstance().getToken());






                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST,Urlnotifymessage,rq1,
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
                                                "Error sending notification, try again later ", Toast.LENGTH_SHORT).show();

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
                if (session.getUserDetails().getRole().equals("freelancer")){
                Intent intent= new Intent(getApplicationContext(), DashboardFreelancer.class);
                startActivity(intent);}
                else{
                    Intent intent= new Intent(getApplicationContext(), DashboardEmployer.class);
                    startActivity(intent);



                }







            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void getlistemployers() {
        g= new Generaladress();

        String UrlListemployers=g.URLfirstpart+"/messages/contactemployers.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,UrlListemployers,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray freelancers =  response.getJSONArray("employers");
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
                                String userimage= c.getString("userimage");

                                String token = c.getString("token");

                                User u=new User();
                                u.setUserid(id);
                                u.setUsername(usermail);
                                u.setFullName(fullname);
                                u.setToken(token);
                                u.setUserimage(userimage);
                                listcontact.add(u);










                            }
                            
                            contactAdapter= new ContactAdapter(getApplicationContext(),R.layout.singlerowcontact,R.id.contactname,listcontact);
                            contactspinner.setAdapter(contactAdapter);


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

    private void getlistfreelancers() {
        g= new Generaladress();

        String UrlListemployers=g.URLfirstpart+"/messages/contactfreelancers.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,UrlListemployers,null,
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
                                String userimage= c.getString("userimage");

                                String token = c.getString("token");
                                User u=new User();
                                u.setUserid(id);
                                u.setUsername(usermail);
                                u.setFullName(fullname);
                                u.setToken(token);
                                u.setUserimage(userimage);
                                listcontact.add(u);










                            }
                            contactAdapter= new ContactAdapter(getApplicationContext(),R.layout.singlerowcontact,R.id.contactname,listcontact);
                            contactspinner.setAdapter(contactAdapter);


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
