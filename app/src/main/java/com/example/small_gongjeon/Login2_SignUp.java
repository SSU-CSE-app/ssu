package com.example.small_gongjeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login2_SignUp extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_passcheck;
    private Button btn_register, btn_id_check;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2_sign_up);

        // 아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_passcheck = findViewById(R.id.et_passcheck);

        //아이디 중복 체크
        btn_id_check = findViewById(R.id.btn_id_check);
        btn_id_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String checkID = et_id.getText().toString();;
                if (validate) {
                    return; //검증완료
                }

                //비어있을 경우
                if (checkID.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                Toast.makeText(getApplicationContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                                validate = true; //검증 완료
                            } else {
                                Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                // 서버로 Volley를 이용해서 요청을 함.
                ValidateRequest validateRequest = new ValidateRequest(checkID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login2_SignUp.this);
                queue.add(validateRequest);
            }
        });

        // 가입하기 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userPassCheck = et_passcheck.getText().toString();

                //한 칸이라도 입력 안했을 경우
                if (userID.equals("") || userPass.equals("") || userName.equals("") || userPassCheck.equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 안한 부분이 있는지 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //아이디 중복 확인했는지
                if (!validate) {
                    Toast.makeText(getApplicationContext(), "중복된 아이디가 있는지 확인하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 비밀번호 재확인
                if (!userPassCheck.equals(userPass)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 재확인 부분을 다시 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login2_SignUp.this, Login1_main.class);
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login2_SignUp.this);
                queue.add(registerRequest);

            }
        });

    }
}