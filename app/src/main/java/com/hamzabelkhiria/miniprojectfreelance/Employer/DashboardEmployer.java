package com.hamzabelkhiria.miniprojectfreelance.Employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.hamzabelkhiria.miniprojectfreelance.LoginActivity;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.SessionHandler;
import com.hamzabelkhiria.miniprojectfreelance.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.io.IOException;

public class DashboardEmployer extends AppCompatActivity {
    private SessionHandler session;
    Toolbar toolbar;
    SpaceNavigationView spaceNavigationView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                //Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                session.logoutUser();
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(DashboardEmployer.this, LoginActivity.class);
                startActivity(i);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_employer);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();



        LayoutInflater layoutInflater= getLayoutInflater();

        View welcometoast= layoutInflater.inflate(R.layout.customtoastmessage, (ViewGroup)findViewById( R.id.toastid));
        ImageView toastimg= findViewById(R.id.toastimg);
        TextView textView= findViewById(R.id.toasttxt);
        toolbar = findViewById(R.id.emptoolbar);
        toolbar.setTitle("                              Home");
        setSupportActionBar(toolbar);


        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        HomeFragment homeFragment= new HomeFragment();
        fragmentTransaction.replace(R.id.fragment,homeFragment);

        fragmentTransaction.commit();
        spaceNavigationView= findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_message_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_work_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_settings_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_profile));
        spaceNavigationView.setCentreButtonSelectable(true);
        spaceNavigationView.setCentreButtonSelected();
        session = new SessionHandler(getApplicationContext());

        //TextView welcomeText = findViewById(R.id.welcomeText);



        Button logoutBtn = findViewById(R.id.btnLogout);


        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(getApplicationContext(),"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                toolbar.setTitle("                              Home");

                spaceNavigationView.setCentreButtonSelectable(true);
                FragmentManager fragmentManager= getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.fragment,homeFragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Fragment f = new Fragment();
                switch(itemIndex) {
                    case 0:
                        f= new MessagesFragment();
                        toolbar.setTitle("                              Messages");

                        break;
                    case 1:
                        f=new FreelancerlistFragment();
                        toolbar.setTitle("                         Available freelancers");

                        // code block
                        break;
                    case 2:
                        f= new PostJobFragment();
                        toolbar.setTitle("                             Post a job");

                        // code block
                        break;
                    case 3:
                        f=  f= new ProfileEmployer();
                        // code block
                        toolbar.setTitle("                             Prolile ");
                        break;





                }
                FragmentManager fragmentManager= getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,f);
                fragmentTransaction.commit();

            }


            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(getApplicationContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        //Button logoutBtn = findViewById(R.id.btnLogout);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
        return true;    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("test intent","part 1 done");
        if (intent != null) {

            int data = intent.getIntExtra("jobid",0);
            String jobtitle = intent.getStringExtra("jobtitle");
//            Log.d("jobtitle",jobtitle);



            if (data != 0) {
                Intent intent1=new Intent(getApplicationContext(), Myapplications.class);
                intent1.putExtra("jobid",data);
                intent1.putExtra("jobtitle",jobtitle);
                startActivity(intent1);

            }
            String notiftype = intent.getStringExtra("type");
            if (notiftype!=null && notiftype.equals("message")) {
                Intent intent1=new Intent(getApplicationContext(), MessagesFragment.class);

                startActivity(intent1);

            }



        }
    }

   /* @Override
    protected void onResume() {
        super.onResume();
         intent As Intent =
        if (this.getIntent() != null) {
Intent intent =Activity.GetStartingIntent;
            int data = intent.getIntExtra("jobid",0);
            String jobtitle = intent.getStringExtra("jobtitle");
           // Log.d("jobtitle",jobtitle);



            if (data != 0) {
                Intent intent1=new Intent(getApplicationContext(), Myapplications.class);
                intent1.putExtra("jobid",data);
                intent1.putExtra("jobtitle",jobtitle);
                startActivity(intent1);

            }


        }

    }*/
}


