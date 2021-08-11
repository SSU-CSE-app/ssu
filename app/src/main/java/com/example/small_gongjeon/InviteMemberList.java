package com.example.small_gongjeon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InviteMemberList extends BaseAdapter {
    ArrayList<Friend> friends = new ArrayList<>();
    CheckBox checkBox;

    //총 몇개?
    @Override
    public int getCount() {
        return friends.size();
    }

    // 데이터 보내기
    @Override
    public Object getItem(int i) {
        return friends.get(i);
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
            view = li.inflate(R.layout.listviewitem_group_add, viewGroup, false);
        }

        ImageView iv = (ImageView)view.findViewById(R.id.image_friend);
        TextView tv1 = (TextView)view.findViewById(R.id.name_friend);
        TextView tv2 = (TextView)view.findViewById(R.id.message_friend);
        checkBox = (CheckBox)view.findViewById(R.id.checkbox_invite_member);

        Friend f = friends.get(i);

        iv.setImageDrawable(f.getD());
        tv1.setText(f.getName());
        tv2.setText(f.getMessage());

        return view;
    }

    public void addFriend(Drawable d, String name, String message){
        Friend f = new Friend();

        f.setD(d);
        f.setName(name);
        f.setMessage(message);

        friends.add(f);
    }

}