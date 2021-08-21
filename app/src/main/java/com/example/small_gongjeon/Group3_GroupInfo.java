package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Group3_GroupInfo extends AppCompatActivity {

    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";

    private String mJsonString;

    // 어댑터 관련 선언
    private ArrayList<Alarm> mArrayList;
    private GroupInfoAlarmAdapter mAdapter;
    private RecyclerView mRecyclerView;
    // 뷰 선언
    private TextView groupName;
    private TextView groupName_main;

    private ImageView mImageView;
    private TextView mTextView;
    private TextView mTextView_request; //TODO 수정

    private Button btn_group_member;
    private Button btn_group_withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group3_group_info);

        // Main.currGroup에서 그룹 이름 가져오기
        groupName = (TextView) findViewById(R.id.tv_group_info_group_name);
        groupName_main = (TextView) findViewById(R.id.name_group);
        groupName.setText(Main.currGroup);
        btn_group_member = (Button)findViewById(R.id.btn_group_member);
        btn_group_withdraw = (Button)findViewById(R.id.btn_group_info_group_withdraw);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_group_info_alarm);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArrayList = new ArrayList<>();

        mAdapter = new GroupInfoAlarmAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        String userId = Main.userID;
        String groupName = Main.currGroup;

        Group3_GroupInfo.GetData task = new Group3_GroupInfo.GetData();
        System.out.println("task execute!! (userId :"+userId+" / groupName :"+groupName+")");
        task.execute( "http://" + IP_ADDRESS + "/alarmlist_in_group_info_request.php", "userId" ,userId, "groupName",groupName);

        // 탈퇴 버튼 누를시 실행
        btn_group_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword_id = userId;
                String keyword_groupName = groupName;
//                System.out.println("id_keyword : "+id_keyword);

                if (keyword_id.equals("")){
                    Toast.makeText(getApplicationContext(), "오류!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (keyword_groupName.equals("")){
                    Toast.makeText(getApplicationContext(), "오류!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String TAG_JSON="webnautes";
                        try {
                            System.out.println("group withdraw\n" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
                            System.out.println("length :"+jsonArray.length());

                            if(jsonArray.length() >= 2){
                                System.out.println("탈퇴 실행됨");
                                Toast.makeText(getApplicationContext(), "그룹을 탈퇴하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                System.out.println("탈퇴 실행 안됨");
                                Toast.makeText(getApplicationContext(), "그룹 탈퇴에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                // 서버로 Volley를 이용해서 요청을 함.
                GroupWithdraw groupWithdraw = new GroupWithdraw(keyword_id,keyword_groupName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Group3_GroupInfo.this);
                queue.add(groupWithdraw);



            }
        });

        // 그룹 멤버 누를시 실행
        btn_group_member.setOnClickListener(new View.OnClickListener() {
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


    }
    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Group3_GroupInfo.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
//            mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

//                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String key_userId = (String) params[1];
            String postParameters_userId = params[2];
            String key_groupName = (String) params[3];
            String postParameters_groupName = params[4];
            String postParameters = key_userId + "=" + postParameters_userId + "&" + key_groupName + "=" + postParameters_groupName;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }
    private void showResult(){

        String TAG_JSON="webnautes";
        String TAG_TIME = "alarm_Time";
        String TAG_PARTICIPANTS = "participants";
        String TAG_ISPAR = "isPar";
        String TAG_ISTER = "isTER";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            System.out.println("jsonAraay : "+jsonArray);
            System.out.println("jsonLength : "+jsonArray.length());

            for(int i=0;i<jsonArray.length();i++){
                System.out.println("currJson: "+jsonArray.getJSONObject(i));

                JSONObject item = jsonArray.getJSONObject(i);

                String time = item.getString(TAG_TIME);
                String participants = item.getString(TAG_PARTICIPANTS);
                Boolean isPar;
                if(item.getString(TAG_ISPAR).equals("1")){ isPar = true; }else { isPar = false;}

                Alarm alarm = new Alarm();

                alarm.setTime(time);
                alarm.setParticipates(participants);
                alarm.setisChecked(isPar);

                mArrayList.add(alarm);
                mAdapter.notifyDataSetChanged();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

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