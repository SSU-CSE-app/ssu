package com.example.small_gongjeon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SetIndIsPar extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://27.96.134.147/set_ind_isPar.php";
    private Map<String, String> map;

    public SetIndIsPar(String userId, String alarmTime, String isPar, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userId", userId);
        map.put("alarmTime", alarmTime);
        map.put("isPar", isPar);
        System.out.println("in userId :"+userId);
        System.out.println("in alarmTime :"+alarmTime);
        System.out.println("in isPar :"+isPar);
    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
