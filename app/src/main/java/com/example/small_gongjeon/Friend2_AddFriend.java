package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend2_AddFriend extends AppCompatActivity {
    private EditText search_keyword;
    ImageButton btn_search_userbyid;

    TextView result_name;
    TextView result_status;
    ImageView result_image; //TODO number로 받아온 photo값에 따라 이미지 부여

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend2_add_friend);

        // 텍스트 뷰 바인딩
        search_keyword = findViewById(R.id.add_friend_keyword);
        result_name = findViewById(R.id.add_friend_result_name);
        result_status = findViewById(R.id.add_friend_result_status);


        // 버튼 바인딩
        btn_search_userbyid = findViewById((R.id.btn_add_friend_search));

        // 클릭 시 실행
        btn_search_userbyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_keyword = search_keyword.getText().toString();
                System.out.println("id_keyword : "+id_keyword);

                if (id_keyword.equals("")){
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("addFriendSearch\n" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String userName = jsonObject.getString("userName");

                            result_name.setText(userName);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                // 서버로 Volley를 이용해서 요청을 함.
                SearchUserById searchUserById = new SearchUserById(id_keyword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Friend2_AddFriend.this);
                queue.add(searchUserById);



            }
        });

                //x버튼 클릭시 종료
                ImageButton finishBtn = findViewById(R.id.close_add_friend_btn);
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