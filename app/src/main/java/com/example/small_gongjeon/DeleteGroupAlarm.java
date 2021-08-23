package com.example.small_gongjeon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteGroupAlarm extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://27.96.134.147/delete_group_alarm.php";
    private Map<String, String> map;

    public DeleteGroupAlarm(String groupName, String alarmTime, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("groupName", groupName);
        map.put("alarmTime", alarmTime);
//        System.out.println("in userId :"+groupName);
//        System.out.println("in alarmTime :"+alarmTime);
    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
