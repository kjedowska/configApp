package com.example.klaudia.configapp;

import android.graphics.Bitmap;

/**
 * Created by Klaudia on 2015-10-27.
 */
public class Configuration {

    private int displayCount = 3;
    private String mode = "";
    private int responseTime = 5;
    private String hintType = "";
    private String level = "poziom1";
    private String proportions = "1:0";
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

    public String getHintType() {
        return hintType;
    }

    public void setHintType(String hintType) {
        this.hintType = hintType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProportions() {
        return proportions;
    }

    public void setProportions(String proportions) {
        this.proportions = proportions;
    }
}
