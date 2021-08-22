package com.example.small_gongjeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmMainGroupAdapter extends RecyclerView.Adapter<AlarmMainGroupAdapter.CustomViewHolder_AlarmMainGroup>{
    private ArrayList<AlarmMain> mList = null;
    private Activity context = null;

    public AlarmMainGroupAdapter(Activity context, ArrayList<AlarmMain> list){
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder_AlarmMainGroup extends  RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView Time;

        public CustomViewHolder_AlarmMainGroup(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.tv_alarm_main_group_alarm_name);
            this.Time = (TextView) view.findViewById(R.id.tv_alarm_main_group_alarm_time);

        }
    }

    @Override
    public CustomViewHolder_AlarmMainGroup onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_group,null);
        CustomViewHolder_AlarmMainGroup viewHolder = new CustomViewHolder_AlarmMainGroup(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder_AlarmMainGroup viewholder, int position) {

        viewholder.name.setText(mList.get(position).getName());
        viewholder.Time.setText(mList.get(position).getTime());
    }
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
