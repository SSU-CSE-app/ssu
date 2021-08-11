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
import androidx.fragment.app.Fragment;

public class Alarm1_main extends Fragment implements View.OnClickListener {
    private View view;
    ImageButton btn_plus_alarm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_alarm1_main,container,false);
        btn_plus_alarm = (ImageButton)view.findViewById(R.id.btn_plus_alarm);
        btn_plus_alarm.setOnClickListener(this);

        ListView lv = view.findViewById(R.id.listview_alarm);
        AlarmList adapter = new AlarmList();

        lv.setAdapter(adapter);

        adapter.addAlarm("09:00","뚱이");
        adapter.addAlarm("09:30","스폰지밥");
        adapter.addAlarm("10:00","다람이");
        adapter.addAlarm("07:00");
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_plus_alarm:
                getActivity().startActivity(new Intent(getActivity(), Alarm2_PlusAlarm.class));
        }

    }
}