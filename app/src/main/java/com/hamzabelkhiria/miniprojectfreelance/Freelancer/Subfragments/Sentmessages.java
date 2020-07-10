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
import com.hamzabelkhiria.miniprojectfreelance.Adapters.MessagesAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.Models.Message;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sentmessages extends Fragment {
    Generaladress g;
    SessionHandler session;
    View view;
    RecyclerView messageslist;
    ArrayList<Message> list = new ArrayList<Message>();

    public Sentmessages() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_sentmessages, container, false);

        messageslist= view.findViewById(R.id.sentrecycler);
        session = new SessionHandler(getContext());
        g= new Generaladress();
        String Urlsent=g.URLfirstpart+"/messages/mysentmessages.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", session.getUserDetails().getUserid());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Urlsent,rq,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray joblist =  response.getJSONArray("messages");
                            Log.d("array saved", joblist.toString());


                            Log.d("array saved", "{"+String.valueOf(response)+"}");
                            for (int i = 0; i < joblist.length(); i++) {
                                JSONObject c = joblist.getJSONObject(i);
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

                                Message msg = new Message(senderid,receiverid,sendertoken,receivertoken,title,object,messagebody,date);
                                msg.setId(id);
                                msg.setSendername(full_name);
                                msg.setSenderimage(userimage);

                                list.add(msg);




                            }
                            MessagesAdapter messagesAdapter = new MessagesAdapter(getContext(),list);
                            messageslist.setLayoutManager(new LinearLayoutManager(getActivity()));
                            messageslist.setAdapter(messagesAdapter);
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
