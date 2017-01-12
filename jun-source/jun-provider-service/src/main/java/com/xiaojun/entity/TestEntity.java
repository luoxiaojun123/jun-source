package com.xiaojun.entity;

import java.io.Serializable;

public class TestEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2853205946920831525L;

	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
