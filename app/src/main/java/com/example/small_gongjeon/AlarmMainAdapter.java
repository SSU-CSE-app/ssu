package com.example.small_gongjeon;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlarmMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<AlarmMain> mlist = null;
    private Activity context = null;

    public AlarmMainAdapter(Activity context, ArrayList<AlarmMain> list){
        this.context = context;
        this.mlist = list;
    }

    class CustomViewHolder_AlarmMain_Group extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView time;

        public CustomViewHolder_AlarmMain_Group(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.tv_alarm_main_group_alarm_name);
            this.time = (TextView) view.findViewById(R.id.tv_alarm_main_group_alarm_time);
        }
    }

    class CustomViewHolder_AlarmMain_Ind extends RecyclerView.ViewHolder{
        protected TextView time;
        protected Switch aSwitch;

        public CustomViewHolder_AlarmMain_Ind(View view) {
            super(view);
            this.time = (TextView) view.findViewById(R.id.tv_alarm_main_individual_alarm_time);
            this.aSwitch = (Switch) view.findViewById(R.id.switch_alarm_main_individual_alarm);
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
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewitem_alarm_individual,null);
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
        }
        else{   // 그룹 알람인 경우
            CustomViewHolder_AlarmMain_Group holder_group = (CustomViewHolder_AlarmMain_Group) viewholder;
            holder_group.time.setText(mlist.get(position).getTime());
            holder_group.name.setText(mlist.get(position).getName());
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
