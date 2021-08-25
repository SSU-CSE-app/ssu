package com.example.small_gongjeon;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

public class Friend {
    private String name;
    private String message;
    private Integer photoID;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPhotoID(Integer photoID) { this.photoID = photoID;}

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPhotoID() {
        return photoID;
    }


}
