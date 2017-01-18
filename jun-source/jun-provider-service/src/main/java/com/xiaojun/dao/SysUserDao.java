package com.xiaojun.dao;

import java.util.Map;

import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;
/**
 * 系统用户dao
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月18日
 */
public interface SysUserDao extends BeseDao<SysUserEntity>{

	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public SysUserEntity selectSysUserByUserName(String username) throws CustomException;
	/**
	 * 根据用户名更新密码
	 * @param map
	 * @return
	 */
	public int updatePassword(Map<String, Object> map) throws CustomException;
}
