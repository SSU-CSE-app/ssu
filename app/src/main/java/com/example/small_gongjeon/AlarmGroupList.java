package com.example.small_gongjeon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmGroupList extends BaseAdapter {
    ArrayList<Alarm_group> alarm_groups = new ArrayList<>();
    ImageView img;

    @Override
    public int getCount() {
        return alarm_groups.size();
    }

    @Override
    public Object getItem(int i) {
        return alarm_groups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context c = viewGroup.getContext();
        if(view == null){
            LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.listviewitem_alarm_group, viewGroup, false);
        }

        img = (ImageView)view.findViewById(R.id.image_alarm);
        TextView tv1 = (TextView)view.findViewById(R.id.alarm_time);
        TextView tv2 = (TextView)view.findViewById(R.id.alarm_group_name);

        Alarm_group g = alarm_groups.get(i);

        tv1.setText(g.getTime());
        tv2.setText(g.getName());

        return view;
    }

    public void addGroupAlarm(String time, String name){
        Alarm_group g = new Alarm_group();

        g.setTime(time);
        g.setName(name);

        alarm_groups.add(g);
    }
}