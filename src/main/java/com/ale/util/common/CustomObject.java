package com.ale.util.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObject extends ObjectMapper{
    public void init(){
        // 排除值为空的属性
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


}
