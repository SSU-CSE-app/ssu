package com.example.small_gongjeon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GroupWithdraw extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://27.96.134.147/group_withdraw_request.php";  //TODO php파일 만들고 넣기
    private Map<String, String> map;


    public GroupWithdraw(String userId, String groupName, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userId",userId);
        map.put("groupName",groupName);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
