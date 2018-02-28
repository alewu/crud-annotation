package com.ale.common.exception;

import static com.ale.common.response.ResponseCode.INTERNAL_SERVER_ERROR;

public enum ServerExceptionEnum implements ExceptionEnum{
    CAPTCHA_SEND_ERROR(INTERNAL_SERVER_ERROR, "验证码发送失败"),
    FILE_UPLOAD_FAILURE(INTERNAL_SERVER_ERROR, "文件上传失败");


    private final Integer code;
    private final String message;

    ServerExceptionEnum(Integer code, String message) {
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
