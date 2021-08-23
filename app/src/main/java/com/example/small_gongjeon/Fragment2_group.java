package com.example.small_gongjeon;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class Fragment2_group extends Fragment {
    private static String IP_ADDRESS = "27.96.134.147";
    private static String TAG = "small_gongjeon";

    private ArrayList groupList = new ArrayList<>();
    private String mJsonString;
    private TimePicker time;
    Spinner spinner_min;
    Spinner spinner_num;
    Spinner spinner_group;

    private CheckBox Mon;
    private CheckBox Tue;
    private  CheckBox Wed;
    private  CheckBox Thu;
    private  CheckBox Fri;
    private  CheckBox Sat;
    private  CheckBox Sun;
    private CheckBox repeat;

    private Integer alarmDay;
    private String isRepeatChecked;
    private String repeatTimes;
    private String repeatMin;
    private String groupName;

    private ImageButton btn_addAlarm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_group, container, false);
        // 버튼 바인딩
        btn_addAlarm = (ImageButton)view.findViewById(R.id.btn_add_group_alarm);

        // 체크박스 바인딩
        Mon = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_MON);
        Tue = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_TUE);
        Wed = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_WED);
        Thu = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_THU);
        Fri = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_FRI);
        Sat = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_SAT);
        Sun = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_SUN);
        repeat = (CheckBox)view.findViewById(R.id.cb_add_group_alarm_repeat);

        // 타임피커 바인딩
        time = (TimePicker)view.findViewById(R.id.tp_add_group_alarm_time);

        // 알람 반복 스피너 (몇분마다)
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("1분마다");
        arrayList.add("3분마다");
        arrayList.add("5분마다");

        spinner_min = (Spinner)view.findViewById(R.id.spinner_add_group_alarm_repeatMin);
        ArrayAdapter<String> minAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner_min.setAdapter(minAdapter);

        //알람 반복 스피너 (몇번반복)
        ArrayList arrayList2 = new ArrayList<>();
        arrayList2.add("1번 반복");
        arrayList2.add("2번 반복");
        arrayList2.add("3번 반복");
        arrayList2.add("4번 반복");
        arrayList2.add("5번 반복");

        spinner_num = (Spinner)view.findViewById(R.id.spinner_add_group_alarm_repeatTime);
        ArrayAdapter<String> numAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList2);
        spinner_num.setAdapter(numAdapter);

        alarmDay = 0;
        isRepeatChecked = "0";

        //그룹 목록
        String Keyword = Main.userID;
        groupList.clear();
        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/group_info_request_final.php", Keyword);

        spinner_group = (Spinner)view.findViewById(R.id.spinner_select_group);


        // 그룹 알람 추가 버튼 누를시 실행
        btn_addAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userId = Main.userID;
                String alarmTime = time.getCurrentHour().toString()+":"+time.getCurrentMinute().toString();
                groupName = spinner_group.getSelectedItem().toString();
                // 요일 숫자로 변환
                if(Mon.isChecked()){
                    alarmDay += 1000000;
                }
                if(Tue.isChecked()){
                    alarmDay += 100000;
                }
                if(Wed.isChecked()){
                    alarmDay += 10000;
                }
                if(Thu.isChecked()){
                    alarmDay += 1000;
                }
                if(Fri.isChecked()){
                    alarmDay += 100;
                }
                if(Sat.isChecked()){
                    alarmDay += 10;
                }
                if(Sun.isChecked()){
                    alarmDay += 1;
                }
                String alarmDays = alarmDay.toString();

                if(repeat.isChecked()){isRepeatChecked="1";}

                repeatTimes = spinner_num.getSelectedItem().toString();

                if(spinner_min.getSelectedItem().toString() == "1분마다"){
                    repeatMin = "1";
                }else if(spinner_min.getSelectedItem().toString() == "3분마다"){
                    repeatMin = "3";
                }else if(spinner_min.getSelectedItem().toString() == "5분마다"){
                    repeatMin = "5";
                }

                if(spinner_num.getSelectedItem().toString() == "1번 반복"){
                    repeatTimes= "1";
                }else if(spinner_num.getSelectedItem().toString() == "2번 반복"){
                    repeatTimes = "2";
                }else if(spinner_num.getSelectedItem().toString() == "3번 반복"){
                    repeatTimes = "3";
                }else if(spinner_num.getSelectedItem().toString() == "4번 반복"){
                    repeatTimes = "4";
                }else if(spinner_num.getSelectedItem().toString() == "5번 반복"){
                    repeatTimes = "5";
                }

                System.out.println("userId:"+userId);
                System.out.println("alarmTime:"+alarmTime);
                System.out.println("alarmDays:"+alarmDays);
                System.out.println("isRep:"+isRepeatChecked);
                System.out.println("reMin:"+repeatMin);
                System.out.println("reTimes:"+repeatTimes);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("insert ind alarm:\n" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isSucceed = jsonObject.getBoolean("success");

                            if( isSucceed){ // 성공 시
                                Toast.makeText(getActivity().getApplicationContext(), "새로운 알람을 등록하였습니다.", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                            else{           // 실패 시
                                Toast.makeText(getActivity().getApplicationContext(), "알람을 등록하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };



                SendAddGroupAlarmRequest sendAddGroupAlarmRequest = new SendAddGroupAlarmRequest(groupName, userId,alarmTime,alarmDays,isRepeatChecked,repeatMin,repeatTimes,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(sendAddGroupAlarmRequest);
            }
        });

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


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            System.out.println("jsonAraay : "+jsonArray);
            System.out.println("jsonLength : "+jsonArray.length());
            groupList.clear();
            for(int i=0;i<jsonArray.length();i++){
//                System.out.println("currJson: "+jsonArray.getJSONObject(i));
                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                //String message = item.getString(TAG_Message);

                groupList.add(name);
            }

            System.out.println("그룹리스트: " + groupList);
            ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, groupList);
            spinner_group.setAdapter(groupAdapter);
            System.out.println("그룹리스트: " + groupList);



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
