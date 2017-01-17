package com.xiaojun.exception;
/**
 * 未知异常
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月17日
 */
@SuppressWarnings("serial")
public class UnknownException extends RuntimeException{

	public UnknownException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnknownException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnknownException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
