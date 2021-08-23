package com.example.small_gongjeon;

import android.graphics.drawable.Drawable;
import android.widget.Switch;

public class AlarmMain {
    private String Name;
    private String Time;
    private String day;
    private boolean isPar;
    private boolean isTer;

    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public boolean getIsPar() {
        return isPar;
    }
    public void setPar(boolean par) {
        isPar = par;
    }

    public boolean getIsTer() {
        return isTer;
    }
    public void setTer(boolean ter) {
        isTer = ter;
    }


}