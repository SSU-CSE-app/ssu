package com.example.small_gongjeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.nfc.TagLostException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend3_request extends AppCompatActivity {

    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";
    private String mJsonString;

    // 어댑터 관련 선언
    private ArrayList<Friend> mArrayList;
    private FriendRequestAdapter mAdapter;
    private RecyclerView mRecyclerView;

    // 뷰 선언
    private ImageView mImageView;
    private TextView mTextView;
    private TextView mTextView_request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend3_request);

        mRecyclerView = (RecyclerView) findViewById(R.id.listview_received_request);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArrayList = new ArrayList<>();

        mAdapter = new FriendRequestAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        String userId = Main.userID;

        Friend3_request.GetData task = new Friend3_request.GetData();
        task.execute( "http://" + IP_ADDRESS + "/get_recieved_friend_request.php", userId);

    


        //x버튼 클릭시 종료
        ImageButton finishBtn = findViewById(R.id.btn_close);
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

            progressDialog = ProgressDialog.show(Friend3_request.this,
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
            String postParameters = "userId=" + params[1];


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
        String TAG_NAME = "requester";
        String TAG_Photo = "requesterPhoto";
        String TAG_ID = "requesterId";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            System.out.println("jsonAraay : "+jsonArray);
            System.out.println("jsonLength : "+jsonArray.length());

            for(int i=0;i<jsonArray.length();i++){
                System.out.println("currJson: "+jsonArray.getJSONObject(i));

                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String photo = item.getString(TAG_Photo);
                String id = item.getString(TAG_ID);
                System.out.println("photo :"+photo);
                Integer photoID = null;
                switch (photo) {
                    case "1" :
                        photoID = R.drawable.group_profile_1_face;
                        break;
                    case "2" :
                        photoID = R.drawable.group_profile_2_smile;
                        break;
                    case "3" :
                        photoID = R.drawable.profile_3_bigsmile;
                        break;
                    case "4" :
                        photoID = R.drawable.profile_4_sad;
                        break;
                    case "5" :
                        photoID = R.drawable.profile_5_smallsad;
                        break;
                    case "6" :
                        photoID = R.drawable.profile_6_smallsmlie;
                        break;
                    case "7" :
                        photoID = R.drawable.profile_7_sick;
                        break;
                    case "8" :
                        photoID = R.drawable.profile_8_bad;
                        break;
                    case "9" :
                        photoID = R.drawable.profile_9_default;
                        break;

                }
                Friend friend = new Friend();

                friend.setName(name);
                friend.setPhotoID(photoID);
                friend.setUserId(id);

                mArrayList.add(friend);
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