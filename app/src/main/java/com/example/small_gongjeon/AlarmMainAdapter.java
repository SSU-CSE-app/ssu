package com.example.small_gongjeon;

import android.app.Activity;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlarmMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<AlarmMain> mlist = null;
    private Activity context = null;

    public AlarmMainAdapter(Activity context, ArrayList<AlarmMain> list){
        this.context = context;
        this.mlist = list;
    }

    // 그룹 알람 뷰 홀더
    class CustomViewHolder_AlarmMain_Group extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView time;
        protected TextView day;

        public CustomViewHolder_AlarmMain_Group(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.tv_alarm_main_group_alarm_name);
            this.time = (TextView) view.findViewById(R.id.tv_alarm_main_group_alarm_time);
            this.day = (TextView) view.findViewById(R.id.tv_alarm_main_group_repeat_day);
        }
    }

    // 개인 알람 뷰 홀더
    class CustomViewHolder_AlarmMain_Ind extends RecyclerView.ViewHolder{
        protected TextView time;
        protected Switch aSwitch;
        protected TextView day;
        protected ImageButton btn_delete_ind_alarm;

        public CustomViewHolder_AlarmMain_Ind(View view) {
            super(view);
            this.time = (TextView) view.findViewById(R.id.tv_alarm_main_individual_alarm_time);
            this.aSwitch = (Switch) view.findViewById(R.id.switch_alarm_main_individual_alarm);
            this.day = (TextView) view.findViewById(R.id.tv_alarm_main_individual_repeat_day);
            this.btn_delete_ind_alarm = (ImageButton) view.findViewById(R.id.btn_delete_ind_alarm);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch(viewType) {
            case 0:     // 그룹 알람인 경우
                Log.d("FFFFF","onCreateViewHolder :"+viewType);
                Log.d("FFFFF","-----그룹 알람인 경우-----");
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_group,null);
                CustomViewHolder_AlarmMain_Group viewHolder_Group = new CustomViewHolder_AlarmMain_Group(view);

                return viewHolder_Group;
            case 1:     // 개인 알람인 경우
                Log.d("FFFFF","onCreateViewHolder :"+viewType);
                Log.d("FFFFF","-----개인 알람인 경우-----");
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_individual,viewGroup,false);
                CustomViewHolder_AlarmMain_Ind viewHolder_Ind = new CustomViewHolder_AlarmMain_Ind(view);

                return viewHolder_Ind;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
        if(mlist.get(position).getName().equals("null")){ // 개인 알람인 경우
            CustomViewHolder_AlarmMain_Ind holder_ind = (CustomViewHolder_AlarmMain_Ind) viewholder;
            holder_ind.time.setText(mlist.get(position).getTime());
            holder_ind.aSwitch.setChecked(mlist.get(position).getIsPar());
            holder_ind.day.setText(mlist.get(position).getDay());

            String alarm_Time = mlist.get(position).getTime();

            holder_ind.btn_delete_ind_alarm.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String key_userId = Main.userID;
                    String key_alarmTime = alarm_Time;

                    System.out.println("key_userId :"+key_userId);
                    System.out.println("key_alarmTime :"+key_alarmTime);

                    Response.Listener<String> reponseListner = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String TAG_JSON="webnautes";
                            try {
                                System.out.println("delete ind\n" + response);

                                JSONObject jsonObject = new JSONObject(response);
                                boolean isSucceed = jsonObject.getBoolean("success");

                                if(isSucceed){  // 삭제 성공
                                    System.out.println("삭제 완료");
//                                    Toast.makeText(getApplicationContext(), "그룹을 탈퇴하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else{       // 실패
                                    System.out.println("삭제 실패");
//                                    Toast.makeText(getApplicationContext(), "그룹 탈퇴에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };


                    DeleteIndAlarm deleteIndAlarm = new DeleteIndAlarm(key_userId,key_alarmTime, reponseListner);
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    queue.add(deleteIndAlarm);
                }
            });

            holder_ind.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    String key_userId = Main.userID;
                    String key_alarmTime = alarm_Time;
                    String key_isPar = "0";
                    if(isChecked){
                        key_isPar = "1";
                    }
                    else{
                        key_isPar = "0";
                    }

                    System.out.println("key_userId :"+key_userId);
                    System.out.println("key_alarmTime :"+key_alarmTime);
                    System.out.println("key_isPar :"+key_isPar);

                    Response.Listener<String> reponseListner = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String TAG_JSON="webnautes";
                            try {
                                System.out.println("response :\n" + response);
                                JSONObject jsonObject = new JSONObject(response);
                                System.out.println("jsonObject : "+jsonObject);
                                JSONObject keyJsonObject = jsonObject.getJSONObject(TAG_JSON);
                                System.out.println("keyJsonObject : "+keyJsonObject);

                                boolean isSucceed = keyJsonObject.getBoolean("success");

                                if(isSucceed){  // 변경 성공
                                    System.out.println("변경 성공");
//                                    Toast.makeText(getApplicationContext(), "그룹을 탈퇴하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else{       // 실패
                                    System.out.println("변경 실패");
//                                    Toast.makeText(getApplicationContext(), "그룹 탈퇴에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };


                    SetIndIsPar setIndIsPar = new SetIndIsPar(key_userId,key_alarmTime,key_isPar , reponseListner);
                    RequestQueue queue = Volley.newRequestQueue(compoundButton.getContext());
                    queue.add(setIndIsPar);
                }
            });
        }
        else{   // 그룹 알람인 경우
            CustomViewHolder_AlarmMain_Group holder_group = (CustomViewHolder_AlarmMain_Group) viewholder;
            holder_group.time.setText(mlist.get(position).getTime());
            holder_group.name.setText(mlist.get(position).getName());
            holder_group.day.setText(mlist.get(position).getDay());
        }

    }

    @Override
    public int getItemViewType(int position) {
        AlarmMain alarmMain = mlist.get(position);
        if (alarmMain.getName().equals("null")){    // 개인 알람인 경우
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return (null != mlist ? mlist.size() : 0);
    }
}
