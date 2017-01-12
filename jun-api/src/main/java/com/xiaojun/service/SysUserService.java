package com.xiaojun.service;
import com.xiaojun.entity.SysUserEntity;
/**
 * 用户服务
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月12日
 */
public interface SysUserService {
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	public SysUserEntity selectSysUserByUserName(String userName);
}
