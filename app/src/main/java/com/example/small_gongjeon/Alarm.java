package com.example.small_gongjeon;

import android.graphics.drawable.Drawable;
import android.widget.Switch;

public class Alarm {
    private String time;
    private String name;
    private Switch onoff;
    private int type;
    private int member;

    public void setMember(int member) {
        this.member = member;
    }

    public int getMember() {
        return member;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}