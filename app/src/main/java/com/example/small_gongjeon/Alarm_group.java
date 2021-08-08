package com.example.small_gongjeon;

import android.graphics.drawable.Drawable;
import android.widget.Switch;

public class Alarm_group {
    private String time;
    private String name;
    private Switch onoff;

    public void setName(String name) {
        this.name = name;
    }

    public void setOnoff(Switch onoff) {
        this.onoff = onoff;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }


    public String getTime() {
        return time;
    }

    public Switch getOnoff() {
        return onoff;
    }
}