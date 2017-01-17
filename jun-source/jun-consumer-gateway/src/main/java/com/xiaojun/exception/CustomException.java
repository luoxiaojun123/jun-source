package com.xiaojun.exception;
/**
 * 自定义异常
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月17日
 */
@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	/**
	 * 异常code
	 */
	private String code;
	/**
	 * 异常提示
	 */
	private String msg;

	public CustomException() {
		super();
	}
	
	public CustomException(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message) {
		super(message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
