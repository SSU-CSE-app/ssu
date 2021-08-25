package com.example.small_gongjeon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SendAddIndAlarmRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://27.96.134.147/insert_ind_alarm.php";
    private Map<String, String> map;


    public SendAddIndAlarmRequest(String userId,String alarmTime, String alarmDay,String repeat,String repeatMin,String repeatNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        String[] timeArray = alarmTime.split(":");
//        System.out.println("timeArray"+timeArray);
        if(Integer.parseInt(timeArray[1])/10 < 1){  // 분 단위가 10보다 작을 경우
            timeArray[1] = "0"+timeArray[1];
        }
        alarmTime = timeArray[0]+":"+timeArray[1];
//        System.out.println("alarmTime : " +alarmTime);

        map = new HashMap<>();
        map.put("alarmTime",alarmTime);
        map.put("alarmDay",alarmDay);
        map.put("userId",userId);
        map.put("alarmRepeat",repeat);
        map.put("repeatTimes",repeatNum);
        map.put("repeatMin",repeatMin);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
