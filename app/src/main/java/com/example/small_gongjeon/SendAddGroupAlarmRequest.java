package com.example.small_gongjeon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SendAddGroupAlarmRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://27.96.134.147/insert_group_alarm.php";
    private Map<String, String> map;


    public SendAddGroupAlarmRequest(String groupName, String userId,String alarmTime, String alarmDay,String repeat,String repeatMin,String repeatNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("groupName",groupName);
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
