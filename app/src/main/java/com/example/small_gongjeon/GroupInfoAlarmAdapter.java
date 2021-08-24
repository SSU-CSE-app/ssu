package com.example.small_gongjeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupInfoAlarmAdapter extends RecyclerView.Adapter<GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm> {


    private ArrayList<Alarm> mList = null;
    private Activity context = null;


    public GroupInfoAlarmAdapter(Activity context, ArrayList<Alarm> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder_GroupInfoAlarm extends RecyclerView.ViewHolder {
        protected TextView alarm_participates;
        protected TextView alarm_time;
        protected Switch alarm_switch;
        protected ImageButton btn_delete_group_alarm;
//        protected ImageView alarm_imageView;



        public CustomViewHolder_GroupInfoAlarm(View view) {
            super(view);
            this.alarm_participates = (TextView) view.findViewById(R.id.tv_group_info_alarm_participants);
            this.alarm_time = (TextView) view.findViewById(R.id.tv_group_info_alarm_time);
            this.alarm_switch = (Switch) view.findViewById(R.id.sw_group_info_alarm_switch);
            this.btn_delete_group_alarm = (ImageButton) view.findViewById(R.id.btn_delete_group_alarm);
//            this.alarm_imageView = (ImageView) view.findViewById(R.id.iv_group_info_image);
        }
    }


    @Override
    public GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_group_info,viewGroup,false);
        GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm viewHolder = new GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm viewholder, int position) {

        viewholder.alarm_participates.setText(mList.get(position).getParticipates());
        viewholder.alarm_time.setText(mList.get(position).getTime());
        viewholder.alarm_switch.setChecked(mList.get(position).getisChecked());
        String alarm_Time = mList.get(position).getTime();

        viewholder.btn_delete_group_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key_groupName = Main.currGroup;
                String key_alarmTime = alarm_Time;

                System.out.println("key_groupName :" + key_groupName);
                System.out.println("key_alarmTime :" + key_alarmTime);

                Response.Listener<String> reponseListner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String TAG_JSON = "webnautes";
                        try {
                            System.out.println("delete groupAlarm\n" + response);

                            JSONObject jsonObject = new JSONObject(response);

                            boolean isSucceed = jsonObject.getBoolean("success");

                            if (isSucceed) {  // 삭제 성공
                                System.out.println("삭제 완료");
//                                    Toast.makeText(getApplicationContext(), "그룹을 탈퇴하였습니다.", Toast.LENGTH_SHORT).show();
                            } else {       // 실패
                                System.out.println("삭제 실패");
//                                    Toast.makeText(getApplicationContext(), "그룹 탈퇴에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                DeleteGroupAlarm deleteGroupAlarm = new DeleteGroupAlarm(key_groupName, key_alarmTime, reponseListner);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(deleteGroupAlarm);
            }
        });

        viewholder.alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String key_userId = Main.userID;
                String key_alarmTime = alarm_Time;
                String key_groupName = Main.currGroup;
                String key_isPar = "0";
                if (isChecked) {
                    key_isPar = "1";
                } else {
                    key_isPar = "0";
                }

//                System.out.println("key_userId :"+key_userId);
//                System.out.println("key_alarmTime :"+key_alarmTime);
//                System.out.println("key_isPar :"+key_isPar);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String TAG_JSON = "webnautes";
                        try {
                            System.out.println("response :\n" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println("jsonObject : " + jsonObject);
                            JSONObject keyJsonObject = jsonObject.getJSONObject(TAG_JSON);
                            System.out.println("keyJsonObject : " + keyJsonObject);

                            boolean isSucceed = keyJsonObject.getBoolean("success");

                            if (isSucceed) {  // 변경 성공
                                System.out.println("변경 성공");
//                                    Toast.makeText(getApplicationContext(), "그룹을 탈퇴하였습니다.", Toast.LENGTH_SHORT).show();
                            } else {       // 실패
                                System.out.println("변경 실패");
//                                    Toast.makeText(getApplicationContext(), "그룹 탈퇴에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                SetGroupIsPar setGroupIsPar = new SetGroupIsPar(key_groupName, key_userId, key_alarmTime, key_isPar, responseListener);
                RequestQueue queue = Volley.newRequestQueue(compoundButton.getContext());
                queue.add(setGroupIsPar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
