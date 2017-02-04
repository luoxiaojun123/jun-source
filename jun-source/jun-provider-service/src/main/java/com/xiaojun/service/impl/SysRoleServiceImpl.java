package com.xiaojun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaojun.dao.SysRoleDao;
import com.xiaojun.entity.SysRoleEntity;
import com.xiaojun.exception.CustomException;
import com.xiaojun.service.SysRoleService;

/**
 * 角色服务
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月4日
 */
@Service(interfaceName = "com.xiaojun.service.SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map) throws CustomException {
		return sysRoleDao.queryList(map);
	}

}
