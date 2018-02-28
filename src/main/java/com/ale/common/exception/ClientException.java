package com.ale.common.exception;

/**
 * @author alewu
 * @date 2017/12/25
 * @description 客户端异常
 */
public class ClientException extends BaseException {

	public ClientException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum);
	}
}
