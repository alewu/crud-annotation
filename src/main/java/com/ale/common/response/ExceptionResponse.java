package com.ale.common.response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2017/10/24 11:07
 * @description 异常响应类
 */
public class ExceptionResponse {
    /*状态码*/
    private Integer code;
    /*消息*/
    private String message;

    Map<String, Object> data = new HashMap<>();


    public ExceptionResponse(){
    }

    private ExceptionResponse(Integer code, String message){
        this.message = message;
        this.code = code;
    }

    //链式操作返回信息
    public ExceptionResponse put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


    public static ExceptionResponse create(Integer code, String message){
        return new ExceptionResponse(code, message);
    }

    public Integer getCode() {  
        return code;  
    }

    public String getMessage() {  
        return message;  
    }  
      
}
