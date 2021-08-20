package com.example.small_gongjeon;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Friend1_main extends Fragment implements View.OnClickListener{
    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";

//    private TextView mTextViewResult;
    private ArrayList<Friend> mArrayList;
    private FriendAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;
    private View view;
    private TextView friend_num;
    ImageButton btn_add_friend;
    ImageButton btn_friend_request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_friend1_main,container,false);


        btn_add_friend = (ImageButton)view.findViewById(R.id.btn_add_friend);
        btn_add_friend.setOnClickListener(this);
        btn_friend_request = (ImageButton)view.findViewById(R.id.btn_friend_request);
        btn_friend_request.setOnClickListener(this);

        friend_num = (TextView)view.findViewById(R.id.tv_friend_num);



//        mTextViewResult = (TextView)view.findViewById(R.id.name_friend);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_friend);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        mArrayList = new ArrayList<>();

        mAdapter = new FriendAdapter(getActivity(), mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();


        String Keyword =  Main.userID;

        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/query.php", Keyword);

        /*
        Button button_search = (Button) view.findViewById(R.id.button_main_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mArrayList.clear();
                mAdapter.notifyDataSetChanged();


                String Keyword =  mEditTextSearchKeyword.getText().toString();
                mEditTextSearchKeyword.setText("");

                GetData task = new GetData();
                task.execute( "http://" + IP_ADDRESS + "/query.php", Keyword);
            }
        });
        */


        return view;
    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
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
        String TAG_NAME = "userName";
        String TAG_Message = "userStatus";
        String TAG_Photo = "userPhoto";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            System.out.println("jsonAraay : "+jsonArray);
            System.out.println("jsonLength : "+jsonArray.length());
            String temp_friend_num = String.valueOf(jsonArray.length());
            friend_num.setText(temp_friend_num);
            for(int i=0;i<jsonArray.length();i++){
//                System.out.println("currJson: "+jsonArray.getJSONObject(i));
                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String message = item.getString(TAG_Message);
                String photo = item.getString(TAG_Photo);
                Integer photoID = null;

                switch (photo) {
                    case "1" :
                        photoID = R.drawable.ic_android_black_50;
                        break;
                    case "2" :
                        photoID = R.drawable.ic_baseline_adb_50;
                        break;
                }

                Friend friend = new Friend();

                friend.setName(name);
                friend.setMessage(message);
                friend.setPhotoID(photoID);

                mArrayList.add(friend);
                mAdapter.notifyDataSetChanged();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_friend:
                getActivity().startActivity(new Intent(getActivity(), Friend2_AddFriend.class));
                return;

            case R.id.btn_friend_request:
                getActivity().startActivity(new Intent(getActivity(), Friend3_request.class));
                break;
        }

    }

}