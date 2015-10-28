package com.example.klaudia.configapp;

import android.graphics.Bitmap;

/**
 * Created by Klaudia on 2015-10-27.
 */
public class Configuration {

    private int displayCount = 3;
    private String mode = "";
    private int responseTime = 5;
    public String type = "";

    Configuration() {}

    Configuration(String type) { this.type = type; }

    public int getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(int displayCount) {
        this.displayCount = displayCount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

}
