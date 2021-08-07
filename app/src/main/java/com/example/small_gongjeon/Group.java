package com.example.small_gongjeon;

import android.graphics.drawable.Drawable;

public class Group {
    private String name;
    private int number;
    private Drawable d;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setD(Drawable d) {
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Drawable getD() {
        return d;
    }


}
