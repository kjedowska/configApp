package com.example.klaudia.configapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Klaudia on 2015-11-06.
 */
public class PreferencesManager {

    SharedPreferences shared;
    public Configuration config;

    PreferencesManager(Activity activity, Configuration config) {
        shared = activity.getSharedPreferences("sharedPref", Context.MODE_WORLD_READABLE);
        this.config = config;
    }

    public void save() {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("mode", config.getMode());
        editor.putInt("responseTime", config.getResponseTime());
        editor.putString("hintType", config.getHintType());
        editor.putInt("displayCount", config.getDisplayCount());
        editor.putString("level", config.getLevel());
        editor.putString("proportions", config.getProportions());
        editor.putBoolean("generalization", config.isGeneralization());
        editor.putInt("automaticRepeats", config.getAutomaticRepeats());
        editor.commit();
    }

    public void read() {
        config.setMode(shared.getString("mode", "auto"));
        config.setHintType(shared.getString("hintType", "fade"));
        config.setDisplayCount(shared.getInt("displayCount", 3));
        config.setResponseTime(shared.getInt("responseTime", 5));
        config.setLevel(shared.getString("level", "poziom1"));
        config.setProportions(shared.getString("proportions", "1:0"));
        config.setGeneralization(shared.getBoolean("generalization", false));
        config.setAutomaticRepeats(shared.getInt("automaticRepeats",2));
    }
}
