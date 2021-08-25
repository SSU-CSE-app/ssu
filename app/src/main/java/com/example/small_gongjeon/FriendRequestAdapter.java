package com.example.small_gongjeon;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.CustomViewHolder_request> {


    private ArrayList<Friend> mList = null;
    private Activity context = null;
    private String requesterId="default";


    public FriendRequestAdapter(Activity context, ArrayList<Friend> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder_request extends RecyclerView.ViewHolder {
        protected TextView name;
        protected ImageView imageView;

        //추가 내용
        protected ImageButton btn_accept_friend;
        protected ImageButton btn_refuse_friend;


        public CustomViewHolder_request(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name_friend_request);
            this.imageView = (ImageView) view.findViewById(R.id.image_friend_request);
            //추가
            this.btn_accept_friend = (ImageButton) view.findViewById(R.id.btn_accept_friend);
            this.btn_refuse_friend = (ImageButton) view.findViewById(R.id.btn_refuse_friend);

            btn_accept_friend.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    String key_userId = Main.userID;
                    String key_requester = requesterId;
                    System.out.println("key_userId :"+key_userId);
                    System.out.println("key_requester :"+key_requester);

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                System.out.println("acceptFriend response:\n" + response);
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) { // 친구추가 성공
                                    Toast.makeText(context, "친구 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                } else { // 친구추가 실패
                                    Toast.makeText(context, "친구 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    // 서버로 Volley를 이용해서 요청을 함.
                    System.out.println("Volley 요청!");
                    AcceptFriendRequest acceptFriendRequest = new AcceptFriendRequest(key_userId,key_requester,responseListener);
                    System.out.println("Volley 요청!");
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    System.out.println("Volley 요청!");
                    queue.add(acceptFriendRequest);
                    System.out.println("Volley 요청!");
                }



            });

            btn_refuse_friend.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    String key_userId = Main.userID;
                    String key_requester = requesterId;
                    System.out.println("key_userId :"+key_userId);
                    System.out.println("key_requester :"+key_requester);

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                System.out.println("refuseFriend response:\n" + response);
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) { // 친구추가 성공
                                    Toast.makeText(context, "친구 요청을 거절하였습니다.", Toast.LENGTH_SHORT).show();
                                } else { // 친구추가 실패
                                    Toast.makeText(context, "친구 요청 거절을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    // 서버로 Volley를 이용해서 요청을 함.
                    System.out.println("Volley 요청!");
                    AcceptFriendRequest acceptFriendRequest = new AcceptFriendRequest(key_userId,key_requester,responseListener);
                    System.out.println("Volley 요청!");
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    System.out.println("Volley 요청!");
                    queue.add(acceptFriendRequest);
                    System.out.println("Volley 요청!");
                }



            });


        }
    }


    @Override
    public CustomViewHolder_request onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_friend_request,viewGroup,false);
        CustomViewHolder_request viewHolder = new CustomViewHolder_request(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.CustomViewHolder_request viewholder, int position) {

        viewholder.name.setText(mList.get(position).getName());
        viewholder.imageView.setImageResource(mList.get(position).getPhotoID());

        this.requesterId = mList.get(position).getUserId();

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
