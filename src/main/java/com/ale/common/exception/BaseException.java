package com.ale.common.exception;

/**
 * @author alewu
 * @date 2017/12/25
 * @description 基础异常
 */
public class BaseException extends Exception {

	private ExceptionEnum exceptionEnum;

	public BaseException(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}

	public ExceptionEnum getExceptionEnum() {
		return exceptionEnum;
	}

	public void setExceptionEnum(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}
}
