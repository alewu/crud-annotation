package com.ale.common.response;
/**
  * @author alewu
  * @date 2018/2/6
  * @description 响应状态码
  */
public interface ResponseCode {
    int OK = 200;
    int BAD_REQUEST = 400;
    int UNAUTHORIZED = 401;
    int NOT_FOUND = 404;
    int INTERNAL_SERVER_ERROR = 500;


}
