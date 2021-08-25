package com.example.small_gongjeon;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Alarm1_main extends Fragment implements View.OnClickListener {
    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";
    private View view;
    ImageButton btn_plus_alarm;
    private Spinner spinner;
    ArrayAdapter<String> arrayAdapter;

    protected ArrayList<AlarmMain> mainArrayList;
    private AlarmMainAdapter mainAdapter;
    private RecyclerView mRecyclerView;
    private String mJsonString;

    public void setMainArrayListClear(){
        this.mainArrayList.clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("알람 메인으로 전환!");
        view=inflater.inflate(R.layout.activity_alarm1_main,container,false);
        btn_plus_alarm = (ImageButton)view.findViewById(R.id.btn_plus_alarm);
        btn_plus_alarm.setOnClickListener(this);

        ArrayList arrayList = new ArrayList<>();
        arrayList.add("     전체");
        arrayList.add("     그룹");
        arrayList.add("     개인");

        spinner = (Spinner)view.findViewById(R.id.spinner_which_alarm);
        arrayAdapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("currType : "+spinner.getSelectedItem().toString());
                mainArrayList.clear();
                GetData afterTask = new GetData();
                if(spinner.getSelectedItem().toString().equals("       개인")){
                    afterTask.execute( "http://" + IP_ADDRESS + "/alarm_ind_list_request.php", Main.userID);
                }
                else if(spinner.getSelectedItem().toString().equals("       그룹")){
                    afterTask.execute( "http://" + IP_ADDRESS + "/alarm_group_list_request.php", Main.userID);
                }
                else if(spinner.getSelectedItem().toString().equals("       전체")){
                    afterTask.execute( "http://" + IP_ADDRESS + "/alarm_list_request.php", Main.userID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_alarm_main_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mainArrayList = new ArrayList<>();

        mainAdapter = new AlarmMainAdapter(getActivity(), mainArrayList);
        mRecyclerView.setAdapter(mainAdapter);

        mainArrayList.clear();


        mainAdapter.notifyDataSetChanged();


        return view;
    }

    protected class GetData extends AsyncTask<String, Void, String> {

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

        String TAG_JSON = "webnautes";
        String TAG_NAME = "groupName";
        String TAG_TIME = "alarmTime";
        String TAG_DAY = "alarmDay";
        String TAG_ISPAR = "isPar";
        String TAG_ISTER = "isTer";

        mainArrayList.clear();
        if(mJsonString != "") {
            try {
                System.out.println("mJsonString :" + mJsonString);
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
                String temp_friend_num = String.valueOf(jsonArray.length());
                System.out.println("length : " + jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    System.out.println("currJson: " + jsonArray.getJSONObject(i));
                    JSONObject item = jsonArray.getJSONObject(i);

                    String name = item.getString(TAG_NAME);
                    String time = item.getString(TAG_TIME);
                    Integer day = item.getInt(TAG_DAY);
                    String temp_day = Integer.toString(day);
                    System.out.println("템프데이: " + temp_day);
                    String repeat_day = "";
                    char[] digit = temp_day.toCharArray();

                    if (temp_day.equals("1111111")) {
                        repeat_day = "매일";
                    } else {
                        if (digit[0] == '1')
                            repeat_day = "월";
                        if (digit[1] == '1')
                            repeat_day = repeat_day + " 화";
                        if (digit[2] == '1')
                            repeat_day = repeat_day + " 수";
                        if (digit[3] == '1')
                            repeat_day = repeat_day + " 목";
                        if (digit[4] == '1')
                            repeat_day = repeat_day + " 금";
                        if (digit[5] == '1')
                            repeat_day = repeat_day + " 토";
                        if (digit[6] == '1')
                            repeat_day = repeat_day + " 일";

                    }
                    System.out.println("리핏데이: " + repeat_day);


                    Boolean isPar;
                    if (item.getString(TAG_ISPAR).equals("1")) {
                        isPar = true;
                    } else {
                        isPar = false;
                    }

                    AlarmMain alarmMain = new AlarmMain();

                    alarmMain.setName(name);
                    alarmMain.setTime(time);
                    alarmMain.setPar(isPar);
                    alarmMain.setDay(repeat_day);

                    mainArrayList.add(alarmMain);
                    mainAdapter.notifyDataSetChanged();

                }

            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "개인 알람이 없습니다!", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_plus_alarm:
                getActivity().startActivity(new Intent(getActivity(), Alarm2_PlusAlarm.class));
        }

    }


}