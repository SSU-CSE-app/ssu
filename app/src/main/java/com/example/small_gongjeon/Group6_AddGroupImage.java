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

        //이미지 클릭 시 해당 이미지로 바꾸기
        ImageView img = findViewById(R.id.image_face);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 1; // 첫번째 이미지 선택, 정보 등록
                Group2_AddGroup.group_iv.setImageResource(R.drawable.ic_baseline_face_24); //이미지 바꾸기
                finish();
            }
        });
        ImageView btn2 = findViewById(R.id.image_smile);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 2;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
                finish();
            }
        });
        ImageView btn3 = findViewById(R.id.image_food);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group2_AddGroup.sig = 3;
                Group2_AddGroup.group_iv.setImageResource(R.drawable.ic_baseline_restaurant_24);
                finish();
            }
        });

    }

}