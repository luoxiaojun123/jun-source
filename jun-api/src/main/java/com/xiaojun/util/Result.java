package com.xiaojun.util;

import java.io.Serializable;

/**
 * 返回结果封装类
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月16日
 * @param <T>
 */
public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6802636125010852798L;
	/**
	 * 返回码
	 */
	private String resCode;
	/**
	 * 返回信息
	 */
	private String resMsg;
	/**
	 * 封装结果
	 */
	private T result;

	public Result() {
		super();
		this.resCode = "200";
		this.resMsg = "成功";
	}

	public Result<T> error(String msg) {
		this.resCode = "500";
		this.resMsg = msg;
		return this;
	}

	public Result(String resCode, String resMsg) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
	}

	public Result(String resCode, String resMsg, T result) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.result = result;
	}

	public String getResCode() {
		return resCode;
	}

	public Result<T> setResCode(String resCode) {
		this.resCode = resCode;
		return this;
	}

	public String getResMsg() {
		return resMsg;
	}

	public Result<T> setResMsg(String resMsg) {
		this.resMsg = resMsg;
		return this;
	}

	public T getResult() {
		return result;
	}

	public Result<T> setResult(T result) {
		this.result = result;
		return this;
	}
}
