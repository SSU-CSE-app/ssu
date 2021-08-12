package com.example.small_gongjeon;

import android.graphics.drawable.Drawable;

public class SpinnerGroupInfo {
    private Drawable d;
    private String name;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public void setD(Drawable d) {
        this.d = d;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public Drawable getD() {
        return d;
    }

    public String getName() {
        return name;
    }
}
