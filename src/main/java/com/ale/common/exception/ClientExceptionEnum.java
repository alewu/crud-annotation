package com.ale.common.exception;

import static com.ale.common.response.ResponseCode.BAD_REQUEST;

public enum ClientExceptionEnum implements ExceptionEnum{
    PHONE_FORMAT_ERROR(BAD_REQUEST, "手机号码格式错误"),
    EMAIL_FORMAT_ERROR(BAD_REQUEST, "电子邮箱格式错误"),
    USER_EXISTED(BAD_REQUEST, "用户已存在"),
    CAPTCHA_ERROR(BAD_REQUEST, "验证码错误"),
    CAPTCHA_EXPIRED(BAD_REQUEST, "验证码已过期"),
    USER_NOT_EXIST(BAD_REQUEST, "用户不存在"),

    FILE_NOT_EMPTY(BAD_REQUEST, "文件不能为空"),
    FILE_TYPE_ERROR(BAD_REQUEST, "文件类型错误"),
    ;



    private final Integer code;
    private final String message;

    ClientExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
