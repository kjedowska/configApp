package com.example.klaudia.configapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Klaudia on 2015-11-04.
 */
public class Mapper {
    static final Map<String, String> hintTypeMapper = new HashMap<String, String>();
    static {
        hintTypeMapper.put("brak", "none");
        hintTypeMapper.put("wygaszanie", "border");
        hintTypeMapper.put("pogrubienie", "fade");
    }

    static final Map<String, Integer> hintIdMapper = new HashMap<String, Integer>();
    static {
        hintIdMapper.put("none", 0);
        hintIdMapper.put("border", 1);
        hintIdMapper.put("fade", 2);
    }

    static final Map<Integer, Integer> displayIdMapper = new HashMap<Integer, Integer>();
    static {
        displayIdMapper.put(2, 0);
        displayIdMapper.put(3, 1);
        displayIdMapper.put(4, 2);
        displayIdMapper.put(6, 3);
    }

    static final Map<String, Integer> levelIdMapper = new HashMap<String, Integer>();
    static {
        levelIdMapper.put("poziom1", 0);
        levelIdMapper.put("poziom2", 1);
        levelIdMapper.put("poziom3", 2);
    }

    static final Map<String, Integer> proportionsIdMapper = new HashMap<String, Integer>();
    static {
        proportionsIdMapper.put("1:0", 0);
        proportionsIdMapper.put("2:1", 1);
        proportionsIdMapper.put("1:1", 2);
        proportionsIdMapper.put("1:2", 3);
        proportionsIdMapper.put("0:1", 4);
    }

    static final Map<String, Integer> setIdMapper = new HashMap<String, Integer>();
    static {
        setIdMapper.put("uczacy", 0);
        setIdMapper.put("generalizacji", 1);
        setIdMapper.put("brak", 2);
    }

    static final Map<String, Integer> statusIdMapper = new HashMap<String, Integer>();
    static {
        statusIdMapper.put("nauczone", 0);
        statusIdMapper.put("uczone", 1);
        statusIdMapper.put("do nauki", 2);
        statusIdMapper.put("pominiete", 3);
    }

    public Integer getHintId(String key) {
        if (hintIdMapper.containsKey(key))
            return hintIdMapper.get(key);
        return 0;
    }

    public String getHint(String key) {
        return hintTypeMapper.get(key);
    }

    public Integer getDisplayId(Integer key) {
        if (displayIdMapper.containsKey(key))
            return displayIdMapper.get(key);
        return 0;
    }

    public Integer getLevelId(String key) {
        if (levelIdMapper.containsKey(key))
            return levelIdMapper.get(key);
        return 0;
    }

    public Integer getProportionsId(String key) {
        if (proportionsIdMapper.containsKey(key))
            return proportionsIdMapper.get(key);
        return 0;
    }

    public Integer getSetId(String key) {
        if (setIdMapper.containsKey(key))
            return setIdMapper.get(key);
        return 0;
    }

    public Integer getStatusId(String key) {
        if (statusIdMapper.containsKey(key))
            return statusIdMapper.get(key);
        return 0;
    }
}
