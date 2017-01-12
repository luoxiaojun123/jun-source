package com.xiaojun.dao;

import com.xiaojun.entity.SysUserEntity;

public interface SysUserDao extends BeseDao<SysUserEntity>{

	/**
	 * 根据姓名查找用户信息
	 * @return
	 */
	public SysUserEntity selectSysUserByUserName(String username);
}
