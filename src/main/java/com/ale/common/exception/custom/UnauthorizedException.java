package com.ale.common.exception.custom;
/**
  * @author alewu
  * @date 2018/2/6
  * @description 未授权异常
  */
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(){
        super();
    }

    public UnauthorizedException(String message){
        super(message);
    }
    
}
