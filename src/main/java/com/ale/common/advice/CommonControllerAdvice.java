package com.ale.common.advice;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author alewu
 * @date 2017/10/24 19:23
 * @description 控制器通知
 */
@ControllerAdvice
public class CommonControllerAdvice {

    /**
     * 此方法用于处理请求信息和处理方法入参日期的转换
     * @param binder binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}
