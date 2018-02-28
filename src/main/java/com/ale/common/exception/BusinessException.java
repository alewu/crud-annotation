package com.ale.common.exception;

public class BusinessException extends RuntimeException implements ExceptionEnum {

	private final Integer code;
	private final String message;

	public BusinessException(Integer code, String message) {
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
