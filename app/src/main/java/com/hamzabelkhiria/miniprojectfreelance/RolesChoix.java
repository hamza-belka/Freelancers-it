package com.hamzabelkhiria.miniprojectfreelance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RolesChoix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_choix);

        Button b1 =findViewById(R.id.btntohire);
        Button b2 =findViewById(R.id.btntowork);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RolesChoix.this,RegisterActivity.class);
                intent.putExtra("role","employer");
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RolesChoix.this,RegisterActivity.class);
                intent.putExtra("role","freelancer");
                startActivity(intent);

            }
        });
    }
}
