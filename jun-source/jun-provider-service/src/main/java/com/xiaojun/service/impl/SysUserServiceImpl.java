package com.xiaojun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaojun.dao.SysUserDao;
import com.xiaojun.dto.UserDTO;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;
import com.xiaojun.service.SysUserService;

/**
 * 系统用户service实现
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月16日
 */
@Service(interfaceName = "com.xiaojun.service.SysUserService")
public class SysUserServiceImpl implements SysUserService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUserEntity selectSysUserByUserName(String username) throws CustomException {
		if (logger.isDebugEnabled()) {
			logger.debug("查询用户信息根据：" + username);
		}
		return sysUserDao.selectSysUserByUserName(username);
	}

	@Override
	public int updatePassword(Map<String, Object> map) throws CustomException {
		int num = sysUserDao.updatePassword(map);
		return num;
	}

	@Override
	public PageInfo<SysUserEntity> queryList(UserDTO dto) throws CustomException {
		PageHelper.startPage(dto.getPage(), dto.getRows());
		Map<String, Object> map = new HashMap<>();
		map.put("userName", dto.getUserName());
		List<SysUserEntity> list = sysUserDao.queryList(map);
		PageInfo<SysUserEntity> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
}
