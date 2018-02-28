package com.ale.common.exception;

import com.ale.common.exception.custom.UnauthorizedException;
import com.ale.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

/**
 * @author alewu
 * @date 2017/10/29 9:03
 * @description 统一异常处理器 在运行时从上往下依次调用每个异常处理方法，
 * 匹配当前异常类型是否与@ExceptionHandler注解所定义的异常相匹配，
 * 若匹配，则忽略后续所有的异常处理方法，最终返回经JSON序列化后的Response对象
 */
@RestControllerAdvice
public class GlobalExceptionAdvice extends GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @Override
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstraintViolationException(ConstraintViolationException e) {
        return super.handleConstraintViolationException(e);
    }

    /**
     * 400 - Bad Request(处理Content-Type
     * "application/x-www-form-urlencoded"提交的内容)
     */
    @Override
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Response handleBindException(BindException e) {
        return super.handleBindException(e);
    }

    /**
     * 400 - Bad Request(处理Content-Type: application/json 提交的内容)
     */
    @Override
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return super.handleMethodArgumentNotValidException(e);
    }

    @Override
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return super.handleHttpMessageNotReadableException(e);
    }

    @Override
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public Response handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        return super.handleHttpMessageNotWritableException(e);
    }

    @Override
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return super.handleHttpRequestMethodNotSupportedException(e);
    }

    @Override
    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public Response handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        return super.handleHttpMediaTypeNotAcceptableException(e);
    }

    @Override
    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return super.handleHttpMediaTypeNotSupportedException(e);
    }

    @Override
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConversionNotSupportedException.class)
    public Response handleConversionNotSupportedException(ConversionNotSupportedException e) {
        return super.handleConversionNotSupportedException(e);
    }

    @Override
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Response handleUnauthorizedException(UnauthorizedException e){
        return super.handleUnauthorizedException(e);
    }

    @Override
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BaseException.class)
    public Response handleBaseException(BaseException e) {
        return super.handleBaseException(e);
    }

    @Override
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(BusinessException e) {
        return super.handleBusinessException(e);
    }

    @Override
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ClientException.class)
    public Response handleClientException(ClientException e) {
        return super.handleClientException(e);
    }

    @Override
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerException.class)
    public Response handleServerException(ServerException e) {
        return super.handleServerException(e);
    }

    @Override
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return super.handleException(e);
    }
}
