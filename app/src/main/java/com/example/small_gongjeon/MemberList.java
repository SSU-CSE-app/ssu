package com.example.small_gongjeon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberList extends BaseAdapter {
    ArrayList<GroupMember> groupMembers = new ArrayList<>();

    //총 몇개?
    @Override
    public int getCount() {
        return groupMembers.size();
    }

    // 데이터 보내기
    @Override
    public Object getItem(int i) {
        return groupMembers.get(i);
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
            view = li.inflate(R.layout.listviewitem_group_member, viewGroup, false);
        }

        ImageView iv = (ImageView)view.findViewById(R.id.image_group_member);
        TextView tv1 = (TextView)view.findViewById(R.id.name_group_member);

        GroupMember m = groupMembers.get(i);

        iv.setImageDrawable(m.getD());
        tv1.setText(m.getName());

        return view;
    }

    public void addMember(Drawable d, String name){
        GroupMember m = new GroupMember();

        m.setD(d);
        m.setName(name);

        groupMembers.add(m);
    }

}
