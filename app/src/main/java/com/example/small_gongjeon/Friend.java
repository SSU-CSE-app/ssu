package com.example.small_gongjeon;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

public class Friend {
    private String name;
    private String message;
    private Drawable d;

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setD(Drawable d) { this.d = d;}

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Drawable getD() {
        return d;
    }


}
