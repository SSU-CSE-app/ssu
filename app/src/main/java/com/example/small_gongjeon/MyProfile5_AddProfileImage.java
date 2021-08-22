package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MyProfile5_AddProfileImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile5_add_profile_image);

        //이미지 클릭 시 해당 이미지로 바꾸기
        ImageView btn1 = findViewById(R.id.image_face);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 1; // 첫번째 이미지 선택, 정보 등록
                MyProfile1_main.profile_iv.setImageResource(R.drawable.group_profile_1_face); //이미지 바꾸기
                finish();
            }
        });
        ImageView btn2 = findViewById(R.id.image_smile);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 2;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.group_profile_2_smile);
                finish();
            }
        });
        ImageView btn3 = findViewById(R.id.image_bigsmile);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 3;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_3_bigsmile);
                finish();
            }
        });
        ImageView btn4 = findViewById(R.id.image_sad);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 4;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_4_sad);
                finish();
            }
        });
        ImageView btn5 = findViewById(R.id.image_smallsad);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 5;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_5_smallsad);
                finish();
            }
        });
        ImageView btn6 = findViewById(R.id.image_smallsmile);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 6;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_6_smallsmlie);
                finish();
            }
        });
        ImageView btn7 = findViewById(R.id.image_sick);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 7;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_7_sick);
                finish();
            }
        });
        ImageView btn8 = findViewById(R.id.image_bad);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 8;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_8_bad);
                finish();
            }
        });
        ImageView btn9 = findViewById(R.id.image_default);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile1_main.profile_image_sig = 9;
                MyProfile1_main.profile_iv.setImageResource(R.drawable.profile_9_default);
                finish();
            }
        });
    }
}