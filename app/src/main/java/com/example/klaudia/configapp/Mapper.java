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

    public Integer getHintId(String key) {
        if (hintIdMapper.containsKey(key))
            return hintIdMapper.get(key);
        return 0;
    }

    public String getHint(String key) {
        return hintTypeMapper.get(key);
    }

}
