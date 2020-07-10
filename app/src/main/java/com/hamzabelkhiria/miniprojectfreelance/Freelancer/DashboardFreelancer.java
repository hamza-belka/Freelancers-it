package com.hamzabelkhiria.miniprojectfreelance.Freelancer;

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

public class DashboardFreelancer extends AppCompatActivity {

    private SessionHandler session;
    Toolbar toolbar;
    SpaceNavigationView spaceNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_freelancer);
        LayoutInflater layoutInflater= getLayoutInflater();



        toolbar = findViewById(R.id.toolbar);
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
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_profile));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_notifications_black_24dp));
        spaceNavigationView.setCentreButtonSelectable(true);
        spaceNavigationView.setCentreButtonSelected();
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView welcomeText = findViewById(R.id.welcomeText);

        welcomeText.setText("Welcome "+user.getUsername()+" "+user.getRole());




        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                toolbar.setTitle("                              Home");
                //Toast.makeText(getApplicationContext(),"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                spaceNavigationView.setCentreButtonSelectable(true);
                FragmentManager fragmentManager= getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                HomeFragment homeFragment= new HomeFragment();
                fragmentTransaction.replace(R.id.fragment,homeFragment);
fragmentTransaction.commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(getApplicationContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                Fragment f = new Fragment();
                FragmentManager fragmentManager= getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();


                switch(itemIndex) {
                    case 0:
                        f= new MessagesFragment();
                        fragmentTransaction.replace(R.id.fragment,f);
                        fragmentTransaction.commit();

                        toolbar.setTitle("                              Messages");


                        Log.d("sdfghj","hellomessages");
                        break;
                    case 1:
                        f=new ListprojectsFragment();
                        fragmentTransaction.replace(R.id.fragment,f);
                        fragmentTransaction.commit();

                        toolbar.setTitle("                         Available Jobs");
                        ;
                        // code block
                        break;
                    case 2:
                        f= new ProfileFragment();
                        fragmentTransaction.replace(R.id.fragment,f);
                        fragmentTransaction.commit();
                        toolbar.setTitle("                             My profile");


                        // code block
                        break;



                }


            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(getApplicationContext(), itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
        return true;
    }

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
                Intent i = new Intent(DashboardFreelancer.this, LoginActivity.class);
                startActivity(i);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

           // Log.d("jobtitle",jobtitle);




                Intent intent1=new Intent(getApplicationContext(), DashboardFreelancer.class);
                startActivity(intent1);





    }
}

