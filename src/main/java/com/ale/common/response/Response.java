package com.ale.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2017/11/12 13:22
 * @description 统一响应结构
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    /** 状态码 */
    private Integer code;
    /** 消息 */
    private String message;
    /** 响应内容 **/
    Map<String, Object> data = new HashMap<>();

    public Response(){
    }

    public Response(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    /** 链式操作返回信息 */
    public Response put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public static Response ok() {
        Response response = new Response();
        response.setCode(ResponseCode.OK);
        return response;
    }

    public static Response failed() {
        Response response = new Response();
        response.setCode(ResponseCode.BAD_REQUEST);
        return response;
    }

    public static Response create(Integer code, String message){
        return new Response(code, message);
    }


}
