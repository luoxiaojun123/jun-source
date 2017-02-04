package com.xiaojun.service;

import java.util.List;
import java.util.Map;

import com.xiaojun.entity.SysRoleEntity;
import com.xiaojun.exception.CustomException;

/**
 * 用户相关角色service
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月4日
 */
public interface SysRoleService {
	/**
	 * 
	 * @return
	 * @throws CustomException
	 */
	public List<SysRoleEntity> queryList(Map<String, Object> map) throws CustomException;
}
