package com.example.small_gongjeon;

import android.app.Activity;
import android.content.Intent;
import android.os.*;

import androidx.appcompat.app.AppCompatActivity;

public class Thumbnail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),Login1_main.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    protected  void onPause() {
        super.onPause();
        finish();
    }
}

