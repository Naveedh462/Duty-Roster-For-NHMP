package com.example.dutyrosterfornhmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class GetStartActivity extends AppCompatActivity {
    private ImageView adminConsole,officerConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
        adminConsole=findViewById(R.id.admin_console);
        officerConsole=findViewById(R.id.officer_console);

        adminConsole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), LoginAdminActivity.class));

            }
        });

        officerConsole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), LoginOfficerActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}