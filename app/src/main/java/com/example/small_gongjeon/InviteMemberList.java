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

        ImageView iv = (ImageView)view.findViewById(R.id.image_invite_friend);
        TextView tv1 = (TextView)view.findViewById(R.id.name_invite_friend);
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkbox_invite_friend);

        Friend f = friends.get(i);

        iv.setImageResource(f.getPhotoID());
        tv1.setText(f.getName());

        return view;
    }

    public void addFriend(Integer photoID, String name){
        Friend f = new Friend();

        f.setPhotoID(photoID);
        f.setName(name);

        friends.add(f);
    }
}