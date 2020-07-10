package com.hamzabelkhiria.miniprojectfreelance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class Messagedetails extends AppCompatActivity {
private TextView sendername, datesent, msgtitle,msgobject, msgbody;
private CircleImageView senderimage;
Button reply;
    Generaladress g;
    SessionHandler session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagedetails);
        session = new SessionHandler(getApplicationContext());

        Intent intent = getIntent();
        int id = intent.getIntExtra("msgid",0);
        sendername=findViewById(R.id.detailssentby);
        senderimage=findViewById(R.id.detailssenderimg);
        datesent=findViewById(R.id.detailsmsgdate);
        msgtitle=findViewById(R.id.detailsmsgtitle);
        msgobject=findViewById(R.id.detailsmsgobject);
        msgbody=findViewById(R.id.detailsmsgbody);
        reply=findViewById(R.id.detailsreply);
        getmsgdetails(id);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SendMessage.class);
                startActivity(intent);
            }
        });







    }

    private void getmsgdetails(int id) {
        g= new Generaladress();
        String Urlmsgdetails=g.URLfirstpart+"/messages/msgdetails.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("messageid", id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlmsgdetails,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject c =  response.getJSONObject("message");




                            // Log.d("fgfg", String.valueOf(edulist.length()));
                            int id = c.getInt("msgid");

                            int senderid = c.getInt("senderid");
                            int receiverid = c.getInt("receiverid");
                            String sendertoken = c.getString("sendertoken");
                            String receivertoken = c.getString("receivertoken");
                            String title = c.getString("title");

                            String object = c.getString("object");
                            String messagebody = c.getString("messagebody");
                            String date = c.getString("date");
                            String full_name = c.getString("fullname");
                            String token = c.getString("token");
                            String userimage = c.getString("userimage");
                            sendername.setText(full_name);
                            datesent.setText(date);


                            msgtitle.setText(title);
                            msgbody.setText(messagebody);
                            msgobject.setText(object);

                            String imagepath= g.URLfirstpart+userimage;
                            new GetImageFromURL(senderimage).execute(imagepath);






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
