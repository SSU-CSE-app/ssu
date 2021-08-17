package com.example.small_gongjeon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Main extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Alarm1_main alarm1_main;
    private Group1_main group1_main;
    private Friend1_main friend1_main;
    private MyProfile1_main myprofile1_main;
    public static String userID;
    public static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 타이틀바 없애기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // user정보 갖고오기
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userName = intent.getStringExtra("userName");

        NavigationBarView navigationView = findViewById(R.id.navigationView);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.alarm_menu:
                        setFrag(0);
                        break;
                    case R.id.group_menu:
                        setFrag(1);
                        break;
                    case R.id.friend_menu:
                        setFrag(2);
                        break;
                    case R.id.MyProfile_menu:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });
        alarm1_main = new Alarm1_main();
        group1_main = new Group1_main();
        friend1_main = new Friend1_main();
        myprofile1_main = new MyProfile1_main();
        setFrag(0);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.frameLayout,alarm1_main);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frameLayout,group1_main);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.frameLayout,friend1_main);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.frameLayout,myprofile1_main);
                ft.commit();
                break;
        }
    }
}