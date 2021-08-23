package com.example.small_gongjeon;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Group1_main extends Fragment implements View.OnClickListener{
    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";

    private ArrayList<Group> mArrayList;
    private GroupAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String mJsonString;
    private View view;
    private TextView group_num;
    ImageButton btn_add_group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_group1_main,container,false);
        btn_add_group = (ImageButton)view.findViewById(R.id.btn_add_group);
        btn_add_group.setOnClickListener(this);


        group_num = (TextView)view.findViewById(R.id.tv_group_num);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_group);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mArrayList = new ArrayList<>();
        mAdapter = new GroupAdapter(getActivity(), mArrayList);

        mAdapter.setOnItemClickListener(new GroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Context c = view.getContext();
                Intent intent = new Intent(c,Group3_GroupInfo.class);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        String Keyword = Main.userID;

        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/group_info_request_final.php", Keyword);


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

            if (result == null) {

//                mTextViewResult.setText(errorString);
            } else {

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
        String TAG_NAME = "group";
        String TAG_NUMBER = "participants";
        String TAG_Photo = "groupPhoto";
        //String TAG_Message = "userStatus";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            System.out.println("jsonAraay : "+jsonArray);
            System.out.println("jsonLength : "+jsonArray.length());
            String temp_group_num = String.valueOf(jsonArray.length());
            group_num.setText("그룹: " + temp_group_num);
            for(int i=0;i<jsonArray.length();i++){
//                System.out.println("currJson: "+jsonArray.getJSONObject(i));
                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                int number = item.getInt(TAG_NUMBER);
                String photo = item.getString(TAG_Photo);
                Integer photoID = null;
                //String message = item.getString(TAG_Message);

                switch (photo) {
                    case "1" :
                        photoID = R.drawable.group_profile_1_face;
                        break;
                    case "2" :
                        photoID = R.drawable.group_profile_2_smile;
                        break;
                    case "3" :
                        photoID = R.drawable.group_3_food;
                        break;
                    case "4" :
                        photoID = R.drawable.group_4_travel;
                        break;
                    case "5" :
                        photoID = R.drawable.group_5_heart;
                        break;
                    case "6" :
                        photoID = R.drawable.group_6_health;
                        break;
                    case "7" :
                        photoID = R.drawable.group_7_game;
                        break;
                    case "8" :
                        photoID = R.drawable.group_8_beer;
                        break;
                    case "9" :
                        photoID = R.drawable.group_9_cloud;
                        break;

                }
                Group group = new Group();

                group.setName(name);
                group.setNumber(number);
                group.setPhoto(photoID);

                mArrayList.add(group);
                mAdapter.notifyDataSetChanged();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_group:
                getActivity().startActivity(new Intent(getActivity(), Group2_AddGroup.class));
        }

    }



}