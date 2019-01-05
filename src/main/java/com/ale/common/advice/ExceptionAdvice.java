package com.ale.common.advice;

import com.ale.common.exception.BusinessException;
import com.ale.common.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * @author alewu
 * @date 2017/10/29 9:03
 * @description 在运行时从上往下依次调用每个异常处理方法，
 * 匹配当前异常类型是否与@ExceptionHandler注解所定义的异常相匹配，
 * 若匹配，则忽略后续所有的异常处理方法，最终返回经JSON序列化后的ExceptionResponse对象
 */
@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 验证参数
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ExceptionResponse handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error("参数验证不通过", e);
        List<Object> messages = new ArrayList<>();
        Set<ConstraintViolation<?>> cs = e.getConstraintViolations();
        for (ConstraintViolation<?> c : cs) {
            messages.add(c.getMessage());
        }
        return ExceptionResponse.create(HttpStatus.BAD_REQUEST.value(), messages.toString());
    }

    /**
     * BusinessException
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BusinessException.class)
    public ExceptionResponse handleBusiness(BusinessException e) {
        LOGGER.error("业务异常", e.getExceptionEnum().getMessage());
        return ExceptionResponse.create(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMessage());
    }

    /**
     * 400 - Bad Request(处理Content-Type: application/json 提交的内容)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<Object> messages = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
            //messages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        LOGGER.error("控制器方法参数无效", e);
        return ExceptionResponse.create(HttpStatus.BAD_REQUEST.value(), "输入参数错误").put("data",map);
    }

    /**
     * 400 - Bad Request(处理Content-Type "application/x-www-form-urlencoded"提交的内容)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ExceptionResponse handleBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> map = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        LOGGER.error("数据绑定异常", e);
        return ExceptionResponse.create(HttpStatus.BAD_REQUEST.value(), "数据绑定异常").put("details",map);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("参数不可读", e);
        return ExceptionResponse.create(HttpStatus.BAD_REQUEST.value(), "参数不可读");
    }


    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("不支持当前请求方法", e);
        return ExceptionResponse.create(HttpStatus.METHOD_NOT_ALLOWED.value(), "不支持当前请求方法");
    }

    /**
     * 406 - Not Acceptable
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ExceptionResponse handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        LOGGER.error("不可接受的媒体类型", e);
        return ExceptionResponse.create(HttpStatus.NOT_ACCEPTABLE.value(), "不可接受的媒体类型");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ExceptionResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        LOGGER.error("不支持当前媒体类型", e);
        return ExceptionResponse.create(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "不支持当前媒体类型");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConversionNotSupportedException.class)
    public ExceptionResponse handleException(ConversionNotSupportedException e) {
        LOGGER.error("数据类型转换异常", e);
        return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据类型转换异常");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ExceptionResponse handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        LOGGER.error("Http消息不可写", e);
        return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Http消息不可写");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception e) {
        LOGGER.error("服务器运行异常", e);
        return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器运行异常");
    }


}
