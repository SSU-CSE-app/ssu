package com.example.small_gongjeon;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupList extends BaseAdapter {
    ArrayList<Group> groups = new ArrayList<>();
    ImageView img;

    //총 몇개?
    @Override
    public int getCount() {
        return groups.size();
    }

    // 데이터 보내기
    @Override
    public Object getItem(int i) {
        return groups.get(i);
    }

    //어디에 있는지
    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context c = viewGroup.getContext();
        if(view == null){
            LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.listviewitem_group, viewGroup, false);
        }

        ImageView iv = (ImageView)view.findViewById(R.id.image_group);
        TextView tv1 = (TextView)view.findViewById(R.id.name_group);
        TextView tv2 = (TextView)view.findViewById(R.id.number_group_member);
        img = (ImageView)view.findViewById(R.id.image_number_group_member);

        Group g = groups.get(i);

        iv.setImageDrawable(g.getD());
        tv1.setText(g.getName());
        tv2.setText("" + g.getNumber());


        return view;
    }

    public void addGroup(Drawable d, String name, int number){
        Group g = new Group();

        g.setD(d);
        g.setName(name);
        g.setNumber(number);

        groups.add(g);
    }
}
