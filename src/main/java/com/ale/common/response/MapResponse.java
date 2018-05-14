package com.ale.common.response;

import java.util.HashMap;

public class MapResponse extends HashMap<String, Object> {
    
    public MapResponse() {
        put("code", 200);
    }

    public static MapResponse ok() {
        MapResponse r = new MapResponse();
        r.put("code", 200);
        return r;
    }


    @Override
    public MapResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
