package com.example.small_gongjeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
//        protected ImageView alarm_imageView;



        public CustomViewHolder_GroupInfoAlarm(View view) {
            super(view);
            this.alarm_participates = (TextView) view.findViewById(R.id.tv_group_info_alarm_participants);
            this.alarm_time = (TextView) view.findViewById(R.id.tv_group_info_alarm_time);
            this.alarm_switch = (Switch) view.findViewById(R.id.sw_group_info_alarm_switch);
//            this.alarm_imageView = (ImageView) view.findViewById(R.id.iv_group_info_image);
        }
    }


    @Override
    public GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_group_info,null);
        GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm viewHolder = new GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupInfoAlarmAdapter.CustomViewHolder_GroupInfoAlarm viewholder, int position) {

        viewholder.alarm_participates.setText(mList.get(position).getParticipates());
        viewholder.alarm_time.setText(mList.get(position).getTime());
        viewholder.alarm_switch.setChecked(mList.get(position).getisChecked());
        System.out.println("ischecked :"+mList.get(position).getisChecked());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
