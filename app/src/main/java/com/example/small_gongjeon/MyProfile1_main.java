package com.example.small_gongjeon;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyProfile1_main extends Fragment implements View.OnClickListener{
    private View view;
    private ImageButton btn_myprofile_notice;
    private ImageButton btn_myprofile_guide;
    private ImageButton btn_myprofile_inquiry;
    private TextView tv_name;
    private TextView tv_status;

    //프로필 사진 추가 관련 선언
    public static int profile_image_sig;
    public static ImageView profile_iv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_my_profile1_main,container,false);

        // 프로필 닉네임 보이게 하기
        tv_name = view.findViewById(R.id.tv_my_profile_name);
        tv_name.setText(Main.userName);

        tv_status = view.findViewById(R.id.tv_my_profile_status);
        tv_status.setText(Main.userStatus);


        btn_myprofile_notice = (ImageButton)view.findViewById(R.id.btn_myprofile_notice);
        btn_myprofile_notice.setOnClickListener(this);
        btn_myprofile_guide = (ImageButton)view.findViewById(R.id.btn_myprofile_guide);
        btn_myprofile_guide.setOnClickListener(this);
        btn_myprofile_inquiry = (ImageButton)view.findViewById(R.id.btn_myprofile_inquiry);
        btn_myprofile_inquiry.setOnClickListener(this);

        //그룹 이미지 추가
        profile_iv = view.findViewById(R.id.profile_picture);
        profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MyProfile5_AddProfileImage.class));

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_myprofile_notice:
                getActivity().startActivity(new Intent(getActivity(), MyProfile2_notice.class));
                break;
            case R.id.btn_myprofile_guide:
                getActivity().startActivity(new Intent(getActivity(), MyProfile3_guide.class));
                break;
            case R.id.btn_myprofile_inquiry:
                getActivity().startActivity(new Intent(getActivity(), MyProfile4_inquiry.class));
                break;
        }

    }
}