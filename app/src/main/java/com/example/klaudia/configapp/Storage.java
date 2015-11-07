package com.example.klaudia.configapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by Klaudia on 2015-11-06.
 */
public class Storage {

    SharedPreferences shared;
    public Configuration nounConfig;
    public Configuration verbConfig;

    Storage(Activity activity, Configuration nounConfig, Configuration verbConfig) {
        shared = activity.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        this.nounConfig = nounConfig;
        this.verbConfig = verbConfig;
    }

    public void save() {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("nMode", nounConfig.getMode());
        editor.putInt("nResponseTime", nounConfig.getResponseTime());
        editor.putString("nHintType", nounConfig.getHintType());
        editor.putInt("nDisplayCount", nounConfig.getDisplayCount());
        editor.putString("nLevel", nounConfig.getLevel());
        editor.putString("nProportions", nounConfig.getProportions());

        editor.putString("vMode", verbConfig.getMode());
        editor.putInt("vResponseTime", verbConfig.getResponseTime());
        editor.putString("vHintType", verbConfig.getHintType());
        editor.putInt("vDisplayCount", verbConfig.getDisplayCount());
        editor.putString("vLevel", verbConfig.getLevel());
        editor.putString("vProportions", verbConfig.getProportions());
        editor.commit();
    }

    public void read() {
        nounConfig.setMode(shared.getString("nMode", "auto"));
        nounConfig.setHintType(shared.getString("nHintType", "fade"));
        nounConfig.setDisplayCount(shared.getInt("nDisplayCount", 3));
        nounConfig.setResponseTime(shared.getInt("nResponseTime", 5));
        nounConfig.setLevel(shared.getString("nLevel", "poziom1"));
        nounConfig.setProportions(shared.getString("nProportions", "1:0"));

        verbConfig.setMode(shared.getString("vMode", "auto"));
        verbConfig.setHintType(shared.getString("vHintType", "fade"));
        verbConfig.setDisplayCount(shared.getInt("vDisplayCount", 3));
        verbConfig.setResponseTime(shared.getInt("vResponseTime", 5));
        verbConfig.setLevel(shared.getString("vLevel", "poziom1"));
        verbConfig.setProportions(shared.getString("vProportions", "1:0"));
    }
}
