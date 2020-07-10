package com.hamzabelkhiria.miniprojectfreelance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Adapters.EduAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Models.Education;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class hb extends AppCompatActivity {

    List<Education> edus;
    RequestQueue rq ;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hb);

        mQueue = Volley.newRequestQueue(this);

        jsonParse();

        RecyclerView recc = (RecyclerView) findViewById(R.id.rec);
        EduAdapter eduAdapter = new EduAdapter(this,edus);
        recc.setLayoutManager(new LinearLayoutManager(getApplication()));
        recc.setAdapter(eduAdapter);





    }

    private void jsonParse() {
        String url = "http://192.168.137.231:8080/member/profiles/listeducation.php";
        JSONObject rq = new JSONObject();
        try {
            //Populate the request parameters
            rq.put("userid", 55);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,rq,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("edulist");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject c = jsonArray.getJSONObject(i);
                                Log.d("fgfg", String.valueOf(jsonArray.length()));
                                int id = c.getInt("id");
                                Log.d("cvgbn", String.valueOf(id));
                                String school = c.getString("school");
                                String degree = c.getString("degree");
                                String startstudydate = c.getString("startstudydate");
                                String endstudydate = c.getString("endstudydate");
                                Education edu = new Education(school,degree,startstudydate,endstudydate);
                                //edu.setId(id);
                                edus.add(edu);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
