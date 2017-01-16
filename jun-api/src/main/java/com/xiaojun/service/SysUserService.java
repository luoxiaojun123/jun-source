package com.xiaojun.service;
import com.xiaojun.entity.SysUserEntity;
/**
 * 系统用户service
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月16日
 */
public interface SysUserService {
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	public SysUserEntity selectSysUserByUserName(String userName);
}
