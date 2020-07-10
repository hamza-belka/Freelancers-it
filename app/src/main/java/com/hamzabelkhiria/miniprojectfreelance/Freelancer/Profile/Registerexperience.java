package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hamzabelkhiria.miniprojectfreelance.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Registerexperience extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    LinearLayout linearLayout;
    List<View> allEds ;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;
    EditText startdate;
    EditText enddate;
    int years, month, day;
    Calendar calander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerexperience);
        mPreferences = getApplicationContext().getSharedPreferences("experiences", Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
        allEds = new ArrayList<View>();
        linearLayout = findViewById(R.id.experienceformcontainer);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.singleexperiencereg, null);
        startdate = rowView.findViewById(R.id.expstartdate);
        enddate = rowView.findViewById(R.id.expenddate);
        // Add the new row before the add field button.
        linearLayout.addView(rowView, linearLayout.getChildCount() - 1);
        allEds.add(rowView);
        FloatingActionButton addedu = findViewById(R.id.addexperience);
        calander = Calendar.getInstance();
        years=calander.get(calander.YEAR);
        month=calander.get(calander.MONTH);
        day=calander.get(calander.DAY_OF_MONTH);
        addedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.singleexperiencereg, null);

                startdate = rowView.findViewById(R.id.expstartdate);
                enddate = rowView.findViewById(R.id.expenddate);

                linearLayout.addView(rowView, linearLayout.getChildCount());
                allEds.add(rowView);
               // if (allEds.size() > 1) {


                startdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Registerexperience.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                month = view.getMonth();
                                String date = view.getDayOfMonth() + "/" + month + "/" + view.getYear();
                                startdate.setText(date);
                            }
                        },years,month,day);
                        datePickerDialog.show();
                    }
                });
                enddate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Registerexperience.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                month = view.getMonth();
                                String date = view.getDayOfMonth() + "/" + month + "/" + view.getYear();
                                enddate.setText(date);
                            }
                        },years,month,day);
                        datePickerDialog.show();
                    }
                });//}
            }
        });
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Registerexperience.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = view.getMonth();
                        String date = view.getDayOfMonth() + "/" + month + "/" + view.getYear();
                        enddate.setText(date);
                    }
                },years,month,day);
                datePickerDialog.show();
            }
        });
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Registerexperience.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = view.getMonth();
                        String date = view.getDayOfMonth() + "/" + month + "/" + view.getYear();
                        startdate.setText(date);
                    }
                },years,month,day);
                datePickerDialog.show();
            }
        });

        Button next = findViewById(R.id.experiencedone);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putInt("number of experiences", allEds.size());
                for (int i = 0; i < allEds.size(); i++) {
                    View currentview = allEds.get(i);
                    EditText exptitle = currentview.findViewById(R.id.exptitle);
                    EditText expcompany = currentview.findViewById(R.id.exptitre);
                    startdate = currentview.findViewById(R.id.expstartdate);
                    enddate = currentview.findViewById(R.id.expenddate);













                    String title = exptitle.getText().toString();
                    String company = expcompany.getText().toString();
                    String datestart = String.valueOf(startdate.getText());
                    String dateend = String.valueOf(enddate.getText());
                    mEditor.putString("exptitle"+i, title);
                    mEditor.putString("expcompany"+i,company);
                    mEditor.putString("datestart"+i,datestart );
                    mEditor.putString("dateend"+i, dateend);
                    mEditor.commit();



                }
                EditText skills = findViewById(R.id.skills);
                String skillsdesc = skills.getText().toString();
                mEditor.putString("skills", skillsdesc);
                mEditor.commit();
                for (int i = 0; i < allEds.size(); i++) {

                    Log.d("test",mPreferences.getString("exptitle"+i,"error"));
                    Log.d("test",mPreferences.getString("expcompany"+i,"error"));
                    Log.d("test",mPreferences.getString("datestart"+i,"error"));
                    Log.d("test",mPreferences.getString("dateend"+i,"error"));
                }
                Log.d("test",mPreferences.getString("skills","error"));
                Intent intent= new Intent(getApplicationContext(), Profilesubmit.class);
                startActivity(intent);

            }
        });
    }
}
