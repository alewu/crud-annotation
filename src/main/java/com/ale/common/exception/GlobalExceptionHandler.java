package com.ale.common.exception;

import com.ale.common.exception.custom.UnauthorizedException;
import com.ale.common.response.ApiResponse;
import com.ale.common.response.Response;
import com.ale.common.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

/**
 * @author alewu
 * @date 2017/12/24
 * @description 统一异常处理实现类
 */
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * spring参数校验
     */
    public ApiResponse handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error("参数验证不通过", e);
        Map<String, Object> details = new HashMap<>();
        e.getConstraintViolations().forEach(c -> details.put(c.getPropertyPath().toString(), c.getMessage()));
        return ApiResponse.builder().data(details).code(BAD_REQUEST.value()).build();
//        return Response.create(BAD_REQUEST.value(), "参数验证不通过").put("details", details);
    }

    /**
     * 输入参数校验
     * 400 - Bad Request(处理Content-Type: application/json 提交的内容)
     */
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("控制器方法参数无效", e);
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> details = new HashMap<>();
        fieldErrors.forEach(fieldError -> details.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return Response.create(BAD_REQUEST.value(), "输入参数错误").put("details", details);
    }

    /**
     * 数据绑定异常
     * 400 - Bad Request(处理Content-Type
     * "application/x-www-form-urlencoded"提交的内容)
     */
    public ApiResponse handleBindException(BindException e) {
        LOGGER.error("数据绑定异常", e);
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> details = new HashMap<>();
        fieldErrors.forEach(fieldError -> details.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ApiResponse.builder().message(details).code(BAD_REQUEST.value()).build();
       // return Response.create(BAD_REQUEST.value(), "数据绑定异常").put("details", details);
    }

    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("参数不可读", e);
        return Response.create(BAD_REQUEST.value(), "参数不可读");
    }

    public Response handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        LOGGER.error("Http消息不可写", e);
        return Response.create(INTERNAL_SERVER_ERROR.value(), "Http消息不可写");
    }

    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("不支持当前请求方法", e);
        return Response.create(METHOD_NOT_ALLOWED.value(), "不支持当前请求方法");
    }

    public Response handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        LOGGER.error("不可接受的媒体类型", e);
        return Response.create(NOT_ACCEPTABLE.value(), "不可接受的媒体类型");
    }

    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        LOGGER.error("不支持当前媒体类型", e);
        return Response.create(UNSUPPORTED_MEDIA_TYPE.value(), "不支持当前媒体类型");
    }

    public Response handleConversionNotSupportedException(ConversionNotSupportedException e) {
        LOGGER.error("数据类型转换异常", e);
        return Response.create(INTERNAL_SERVER_ERROR.value(), "数据类型转换异常");
    }

    /**
     * 业务异常
     */
    public Response handleBaseException(BaseException e) {
        LOGGER.error("基础异常", e);
        return Response.create(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMessage());
    }

    public Response handleBusinessException(BusinessException e) {
        LOGGER.error("业务异常", e);
        return Response.create(e.getCode(), e.getMessage());
    }

    /**
     * 客户端业务异常
     */
    public Response handleClientException(ClientException e) {
        LOGGER.error("客户端业务异常", e);
        return Response.create(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMessage());
    }

    /**
     * 服务器业务异常
     */
    public Response handleServerException(ServerException e) {
        LOGGER.error("服务器业务异常", e);
        return Response.create(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMessage());
    }

    /**
     * 未授权异常
     */
    public Response handleUnauthorizedException(UnauthorizedException e) {
        LOGGER.error("用户未授权", e);
        return Response.create(ResponseCode.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 服务器运行异常
     */
    public Response handleException(Exception e) {
        LOGGER.error("服务器运行异常", e);
        return Response.create(INTERNAL_SERVER_ERROR.value(), "服务器运行异常");
    }
}
