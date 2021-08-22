package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Alarm2_PlusAlarm extends AppCompatActivity {
    Fragment fragment1,fragment2;
    Spinner spinner_min;
    Spinner spinner_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm2_plus_alarm);


        // 개인/그룹 탭 전환
        fragment1 = new Fragment1_individual();
        fragment2 = new Fragment2_group();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment1).commit();
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
/*        // 알람 반복 스피너 (몇분마다, 몇번반복)
        spinner_min = findViewById(R.id.spinner_alarm_min);
        spinner_num = findViewById(R.id.spinner_alarm_num);

        ArrayAdapter minAdapter = ArrayAdapter.createFromResource(this,R.array.repeat_alarm_min, android.R.layout.simple_spinner_dropdown_item);
        spinner_min.setAdapter(minAdapter);

        ArrayAdapter numAdapter = ArrayAdapter.createFromResource(this,R.array.repeat_alarm_num, android.R.layout.simple_spinner_dropdown_item);
        spinner_num.setAdapter(numAdapter);
*/
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                Fragment selected = null;
                if(position == 0){
                    selected = fragment1;

                }
                if(position == 1)
                    selected = fragment2;

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.close_plus_alarm_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //배경 클릭시 꺼지는거 막기
        if( event.getAction() == MotionEvent.ACTION_OUTSIDE ) {
            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}