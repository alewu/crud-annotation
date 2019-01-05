package com.ale.common.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception {

    private ExceptionEnum exceptionEnum;

    public BusinessException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

}
