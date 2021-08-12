package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

public class Group3_GroupInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group3_group_info);

        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.close_group_info_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView lv = findViewById(R.id.listview_groupinfo);
        Spinner sp = findViewById(R.id.spinner_group);

        AlarmList adapter = new AlarmList();
        SpinnerGroupInfoList adapter2 = new SpinnerGroupInfoList();

        lv.setAdapter(adapter);
        sp.setAdapter(adapter2);
        //sp.setSelection(adapter2.getCount()-1);

        adapter.addAlarm(7,"08:00");
        adapter.addAlarm(13,"13:00");

        adapter2.addSpinnerGroup(ContextCompat.getDrawable(this,R.drawable.ic_launcher_background),"이선호");
        adapter2.addSpinnerGroup(ContextCompat.getDrawable(this,R.drawable.tap_friends),"이민지");
        adapter2.addSpinnerGroup(ContextCompat.getDrawable(this,R.drawable.tap_friends),"김흥수");
        adapter2.addSpinnerGroup("그룹 멤버");

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