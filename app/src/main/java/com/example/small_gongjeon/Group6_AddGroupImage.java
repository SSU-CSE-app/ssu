package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Group6_AddGroupImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group6_add_group_image);

        //이미지 클릭 시 해당 이미지 정보 주기
        ImageView img = findViewById(R.id.image_face);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 1;
                finish();
            }
        });
        ImageView btn2 = findViewById(R.id.image_smile);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 2;
                finish();
            }
        });
        ImageView btn3 = findViewById(R.id.image_food);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 3;
                finish();
            }
        });

/*
        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.close_add_group_image_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    }
}