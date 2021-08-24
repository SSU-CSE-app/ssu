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

public class AlarmMainIndividualAdapter extends RecyclerView.Adapter<AlarmMainIndividualAdapter.CustomViewHolder_AlarmMainIndividual>{
    private ArrayList<AlarmMain> mList = null;
    private Activity context = null;

    public AlarmMainIndividualAdapter(Activity context, ArrayList<AlarmMain> list){
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder_AlarmMainIndividual extends RecyclerView.ViewHolder{
        protected TextView Time;
        protected Switch aSwitch;
        protected TextView day;

        public CustomViewHolder_AlarmMainIndividual(View view) {
            super(view);
            this.Time = (TextView) view.findViewById(R.id.tv_alarm_main_individual_alarm_time);
            this.aSwitch = (Switch) view.findViewById(R.id.switch_alarm_main_individual_alarm);
            this.day = (TextView) view.findViewById(R.id.tv_alarm_main_individual_repeat_day);
        }
    }

    @Override
    public CustomViewHolder_AlarmMainIndividual onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_individual,viewGroup,false);
        CustomViewHolder_AlarmMainIndividual viewHolder = new CustomViewHolder_AlarmMainIndividual(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder_AlarmMainIndividual viewholder, int position) {
        viewholder.Time.setText(mList.get(position).getTime());
        viewholder.aSwitch.setChecked(mList.get(position).getIsPar());
        viewholder.day.setText(mList.get(position).getDay());

    }
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
