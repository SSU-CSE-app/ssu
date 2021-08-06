package com.example.small_gongjeon;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Friend1_main extends Fragment implements View.OnClickListener{
    private View view;
    ImageButton btn_add_friend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_friend1_main,container,false);
        btn_add_friend = (ImageButton)view.findViewById(R.id.btn_add_friend);
        btn_add_friend.setOnClickListener(this);
        ListView lv = view.findViewById(R.id.listview_friend);
        FriendList adapter = new FriendList();

        lv.setAdapter(adapter);

        adapter.addFriend(ContextCompat.getDrawable(getActivity(),R.drawable.ic_launcher_background), "이선호", "좀 되라");
        adapter.addFriend(ContextCompat.getDrawable(getActivity(),R.drawable.tap_friends), "이민지", "하이하이");
        adapter.addFriend(ContextCompat.getDrawable(getActivity(),R.drawable.tap_friends), "김흥수", "!!!!!!!");
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_friend:
                getActivity().startActivity(new Intent(getActivity(), Friend2_AddFriend.class));
        }

    }
}