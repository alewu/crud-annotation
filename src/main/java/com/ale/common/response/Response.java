package com.ale.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2017/11/12 13:22
 * @description 统一响应结构
 */
@Data
public class Response {

    /**
     * 状态码
     **/
    private Integer code;

    /**
     * 响应内容
     **/
    Map<String, Object> data = new HashMap<>();

    private Response() {
    }

    //链式操作返回信息
    public Response put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public static Response ok() {
        Response response = new Response();
        response.setCode(200);
        return response;
    }

    public static Response failed() {
        Response response = new Response();
        response.setCode(400);
        return response;
    }


}
