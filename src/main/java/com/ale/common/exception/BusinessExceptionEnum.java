package com.ale.common.exception;

public enum BusinessExceptionEnum implements ExceptionEnum {

    UserNotExist(404, "用户不存在"),
    PasswordError(400, "密码错误"),
    UserExisted(500, "用户已存在"),
    PhoneFormatError(404, "请填写正确的手机号码"),
    CaptchaSendFailed(404, "验证码发送失败"),
    CaptchaError(400, "验证码错误"),
    CaptchaExpired(400, "验证码已过期"),
    PhoneOrCaptchaError(400, "请填写正确的手机号或验证码"),
    AdvertisementPostFailed(500, "无发布的广告"),

    FileIsEmpty(400, "文件不能为空"),
    FileTypeError(400, "文件类型错误");

    private final Integer code;
    private final String message;

    BusinessExceptionEnum(Integer code, String message) {
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
