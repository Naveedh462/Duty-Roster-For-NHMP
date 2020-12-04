package com.example.dutyrosterfornhmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import AdminOfficer.OrderListActivity;
import AdminOfficer.OrdersActivity;
import Officers.RotationLeaveActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, RotationLeaveActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}