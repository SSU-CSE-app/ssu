package com.example.small_gongjeon;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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