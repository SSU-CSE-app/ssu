package com.example.small_gongjeon;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Group1_main extends Fragment implements View.OnClickListener{
    private View view;
    ImageButton btn_add_group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_group1_main,container,false);
        btn_add_group = (ImageButton)view.findViewById(R.id.btn_add_group);
        btn_add_group.setOnClickListener(this);


        ListView lv = view.findViewById(R.id.listview_group);
        GroupList adapter = new GroupList();

        lv.setAdapter(adapter);

        adapter.addGroup(ContextCompat.getDrawable(getActivity(),R.drawable.ic_launcher_background), "뚱이", 7);
        adapter.addGroup(ContextCompat.getDrawable(getActivity(),R.drawable.tap_friends), "파워레인저", 5);
        adapter.addGroup(ContextCompat.getDrawable(getActivity(),R.drawable.tap_profile), "소공전", 3);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getActivity().startActivity(new Intent(getActivity(), Group3_GroupInfo.class));
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_group:
                getActivity().startActivity(new Intent(getActivity(), Group2_AddGroup.class));
        }

    }


}