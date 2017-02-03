package com.xiaojun.dto;

import java.io.Serializable;

public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6270203485004976967L;
	/**
	 * 请求页码数
	 */
	private Integer page;
	/**
	 * 请求多少行数
	 */
	private Integer rows;

	public Integer getPage() {
		if (page == null) {
			return 1;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		if (rows == null) {
			return 10;
		}
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
