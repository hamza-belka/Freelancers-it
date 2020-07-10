package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hamzabelkhiria.miniprojectfreelance.Freelancer.DashboardFreelancer;
import com.hamzabelkhiria.miniprojectfreelance.R;

public class Profilesubmit extends AppCompatActivity {
    Submittodb submittodb;
    Button submit;
    EditText profiletitle, about, location;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesubmit);
        mPreferences = getApplicationContext().getSharedPreferences("additionnal infos", Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
        submittodb = new Submittodb(getApplicationContext());


        submit = findViewById(R.id.submit);

        profiletitle = findViewById(R.id.profiletitle);
        about = findViewById(R.id.about);
        location = findViewById(R.id.location);

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

                mPreferences = getApplicationContext().getSharedPreferences("studies", Context.MODE_PRIVATE);
                int numberstudies = mPreferences.getInt("number of studies",0);
                for (int i = 0; i < numberstudies; i++) {
                    String schoolname = mPreferences.getString("schoolname"+i,"error");
                    String degreename = mPreferences.getString("degreename"+i,"error");
                    String datestart = mPreferences.getString("datestart"+i,"error");
                    String dateend = mPreferences.getString("dateend"+i,"error");

                    submittodb.submitedu(getApplicationContext(),schoolname,degreename,datestart,dateend);





                }
                mPreferences = getApplicationContext().getSharedPreferences("experiences", Context.MODE_PRIVATE);
                int numberexp = mPreferences.getInt("number of experiences",0);
                for (int i = 0; i < numberexp; i++) {
                    String title = mPreferences.getString("exptitle"+i,"error");
                    String company = mPreferences.getString("expcompany"+i,"error");
                    String datestart = mPreferences.getString("datestart"+i,"error");
                    String dateend = mPreferences.getString("dateend"+i,"error");
                    submittodb.submitexp(getApplicationContext(),title,company,datestart,dateend);





                }
                String skills= mPreferences.getString("skills","error");
                submittodb.submitskill(getApplicationContext(),skills);

                Intent intent= new Intent(getApplicationContext(), DashboardFreelancer.class);
                startActivity(intent);
                finish();











            }
        });



    }
}
