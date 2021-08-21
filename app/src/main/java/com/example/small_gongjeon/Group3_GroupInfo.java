package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Group3_GroupInfo extends AppCompatActivity {
    private TextView groupName;
    private TextView groupName_main;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group3_group_info);

        groupName = (TextView) findViewById(R.id.tv_group_info_group_name);
        groupName_main = (TextView) findViewById(R.id.name_group);
        groupName.setText(Main.currGroup);
        btn = (Button)findViewById(R.id.btn_group_member);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GroupMember.class);
                startActivity(intent);
            }
        });

        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.close_group_info_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView lv = findViewById(R.id.listview_groupinfo);

        AlarmList adapter = new AlarmList();

        lv.setAdapter(adapter);

        adapter.addAlarm(7,"08:00");
        adapter.addAlarm(13,"13:00");


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