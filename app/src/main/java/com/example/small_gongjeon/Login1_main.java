package com.example.small_gongjeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login1_main extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        // 타이들바 없애기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);


        // 회원가입 버튼을 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login1_main.this, Login2_SignUp.class);
                startActivity(intent);
            }
        });

        // 로그인 버튼 클릭 시 수행
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String TAG_JSON="webnautes";
                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("response :\n" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println("jsonObject : "+jsonObject);
                            JSONObject keyJsonObject = jsonObject.getJSONObject(TAG_JSON);
                            System.out.println("keyJsonObject : "+keyJsonObject);
//                            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
//                            System.out.println("jsonAraay : "+jsonArray);
//                            System.out.println("jsonLength : "+jsonArray.length());

                            boolean success = keyJsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String userID = keyJsonObject.getString("userId");
                                String userPass = keyJsonObject.getString("userPassword");
                                String userName = keyJsonObject.getString("userName");
                                String userPhoto = keyJsonObject.getString("userPhoto");
                                String userStatus = keyJsonObject.getString("userStatus");

                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();

                                //화면전환 코드
                                Intent intent = new Intent(Login1_main.this, Main.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userPhoto", userPhoto);
                                intent.putExtra("userStatus", userStatus);

                                startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login1_main.this);
                queue.add(loginRequest);
            }
        });


    }
}