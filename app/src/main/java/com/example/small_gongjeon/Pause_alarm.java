package com.example.small_gongjeon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pause_alarm extends AppCompatActivity{

    private Button btn_show_member;
    private Button btn_pause_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause_alarm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_show_member = (Button) findViewById(R.id.btn_show_member);
        btn_pause_alarm = (Button) findViewById(R.id.btn_pause_alarm);

        btn_show_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Pause_alarm_member.class);
                startActivity(intent);
            }
        });

        btn_pause_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}