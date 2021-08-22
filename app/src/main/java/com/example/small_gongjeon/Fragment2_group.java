package com.example.small_gongjeon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment2_group extends Fragment {
    Spinner spinner_min;
    Spinner spinner_num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_group, container, false);

        // 알람 반복 스피너 (몇분마다)

        ArrayList arrayList = new ArrayList<>();
        arrayList.add("1분마다");
        arrayList.add("3분마다");
        arrayList.add("5분마다");

        spinner_min = (Spinner)view.findViewById(R.id.spinner_alarm_min);
        ArrayAdapter<String> minAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner_min.setAdapter(minAdapter);

        //알람 반복 스피너 (몇번반복)

        ArrayList arrayList2 = new ArrayList<>();
        arrayList2.add("1번 반복");
        arrayList2.add("2번 반복");
        arrayList2.add("3번 반복");
        arrayList2.add("4번 반복");
        arrayList2.add("5번 반복");

        spinner_num = (Spinner)view.findViewById(R.id.spinner_alarm_num);
        ArrayAdapter<String> numAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList2);
        spinner_num.setAdapter(numAdapter);

        return view;
    }
}
