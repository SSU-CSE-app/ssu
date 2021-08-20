package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend3_request extends AppCompatActivity {

    ImageButton btn_accept_friend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend3_request);

        btn_accept_friend = findViewById(R.id.btn_accept_friend);

        btn_accept_friend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String requesterId = Main.userID;
                //todo 이거 DB에서 받아온거 위에 줄 처럼 해야할듯.
                //String receiverId =
                System.out.println("ㅡㅡrequesterㅡㅡ\n"+requesterId);
                //System.out.println("--receiver--\n"+receiverId);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            System.out.println("친구 수락\n"+response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isSucceed = jsonObject.getBoolean("success");

                            if( isSucceed){ // 성공 시
                                Toast.makeText(getApplicationContext(), "친구 요청을 수락했습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else{           // 실패 시
                                Toast.makeText(getApplicationContext(), "친구 요청 수락을 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                //VOLLEY
                AcceptFriendRequest acceptFriendRequest = new AcceptFriendRequest(requesterId, receiverId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Friend3_request.this);
                queue.add(acceptFriendRequest);

            }

        });



        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.btn_close);
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