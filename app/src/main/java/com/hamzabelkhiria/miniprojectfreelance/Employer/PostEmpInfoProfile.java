package com.hamzabelkhiria.miniprojectfreelance.Employer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Submittodb;
import com.hamzabelkhiria.miniprojectfreelance.R;

public class PostEmpInfoProfile extends AppCompatActivity {
    Submittodb submittodb;
    Button submit;
    EditText profiletitle, about, location;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_emp_info_profile);
        mPreferences = getApplicationContext().getSharedPreferences("additionnal infos", Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
        submittodb = new Submittodb(getApplicationContext());
        submit = findViewById(R.id.submitempprofile);

        profiletitle = findViewById(R.id.empprofiletitle);
        about = findViewById(R.id.empabout);
        location = findViewById(R.id.emplocation);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = profiletitle.getText().toString();
                String aboutinfo = about.getText().toString();
                String adress = location.getText().toString();

                mEditor.putString("title", titre);
                mEditor.putString("about",aboutinfo);
                mEditor.putString("location",adress );
                mEditor.commit();

                submittodb.submitinfo(getApplicationContext(),titre,aboutinfo,adress);
                Intent intent= new Intent(getApplicationContext(), DashboardEmployer.class);
                startActivity(intent);
                finish();
            }
        });



    }



}
