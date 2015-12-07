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
    private String level = "level1";
    private String proportions = "1:0";
    private boolean generalization = false;
    public String type = "";
    private int automaticRepeats = 2;

    Configuration() {
    }

    Configuration(String type) {
        this.type = type;
    }

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

    public boolean isGeneralization() {
        return generalization;
    }

    public void setGeneralization(boolean generalization) {
        this.generalization = generalization;
    }

    public int getAutomaticRepeats() {
        return automaticRepeats;
    }

    public void setAutomaticRepeats(int automaticRepeats) {
        this.automaticRepeats = automaticRepeats;
    }
}
