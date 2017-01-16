package com.xiaojun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaojun.dao.SysUserDao;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.service.SysUserService;

/**
 * 系统用户service实现
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月16日
 */
@Service(interfaceName = "com.xiaojun.service.SysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUserEntity selectSysUserByUserName(String username) {
		return sysUserDao.selectSysUserByUserName(username);
	}

}
