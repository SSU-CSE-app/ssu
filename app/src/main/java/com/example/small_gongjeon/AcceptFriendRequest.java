package com.example.small_gongjeon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AcceptFriendRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://27.96.134.147/final_friend_request00.php";     //TODO php주소 수정
    private Map<String, String> map;

    // requesterId : 요청 보내는 사람    receiverId : 요청 받는 사람
    public AcceptFriendRequest(String requesterId, String receiverId, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("requesterId", requesterId);
        map.put("receiverId", receiverId);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
