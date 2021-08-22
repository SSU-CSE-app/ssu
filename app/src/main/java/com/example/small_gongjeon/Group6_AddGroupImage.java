package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Group6_AddGroupImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group6_add_group_image);

        //이미지 클릭 시 해당 이미지로 바꾸기
        ImageView btn1 = findViewById(R.id.image_face);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 1; // 첫번째 이미지 선택, 정보 등록
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_1_face); //이미지 바꾸기
                finish();
            }
        });
        ImageView btn2 = findViewById(R.id.image_smile);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 2;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_2_smile);
                finish();
            }
        });
        ImageView btn3 = findViewById(R.id.image_food);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 3;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_3_food);
                finish();
            }
        });
        ImageView btn4 = findViewById(R.id.image_travel);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 4; // 첫번째 이미지 선택, 정보 등록
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_4_travel); //이미지 바꾸기
                finish();
            }
        });
        ImageView btn5 = findViewById(R.id.image_heart);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 5;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_5_heart);
                finish();
            }
        });
        ImageView btn6 = findViewById(R.id.image_health);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 6;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_6_health);
                finish();
            }
        });
        ImageView btn7 = findViewById(R.id.image_game);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 7; // 첫번째 이미지 선택, 정보 등록
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_7_game); //이미지 바꾸기
                finish();
            }
        });
        ImageView btn8 = findViewById(R.id.image_beer);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 8;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_8_beer);
                finish();
            }
        });
        ImageView btn9 = findViewById(R.id.image_cloud);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 9;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.group_9_cloud);
                finish();
            }
        });
    }

}