package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

public class Group2_AddGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group2_add_group);

        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.close_add_group_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView lv = findViewById(R.id.listview_group_add);
        InviteMemberList adapter = new InviteMemberList();

        lv.setAdapter(adapter);

        adapter.addFriend(ContextCompat.getDrawable(this,R.drawable.ic_launcher_background), "이선호", "좀 되라");
        adapter.addFriend(ContextCompat.getDrawable(this,R.drawable.tap_friends), "이민지", "하이하이");
        adapter.addFriend(ContextCompat.getDrawable(this,R.drawable.tap_friends), "김흥수", "!!!!!!!");
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