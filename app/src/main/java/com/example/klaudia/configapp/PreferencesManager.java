package com.example.klaudia.configapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

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

        editor.commit();
    }

    public void read() {
        config.setMode(shared.getString("nMode", "auto"));
        config.setHintType(shared.getString("nHintType", "fade"));
        config.setDisplayCount(shared.getInt("nDisplayCount", 3));
        config.setResponseTime(shared.getInt("nResponseTime", 5));
        config.setLevel(shared.getString("nLevel", "poziom1"));
        config.setProportions(shared.getString("nProportions", "1:0"));
    }
}
