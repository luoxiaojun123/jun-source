package com.xiaojun.dao;

import java.util.List;
import java.util.Map;

import com.xiaojun.entity.SysRoleEntity;
import com.xiaojun.exception.CustomException;

/**
 * 系统用户角色dao
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月18日
 */
public interface SysRoleDao extends BeseDao<SysRoleEntity> {
	/**
	 * 根据id查询角色所包含权限
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	public List<Integer> queryMenuByRoleId(Integer id) throws CustomException;
	/**
	 * 保存角色菜单关联
	 * @throws CustomException
	 */
	public void saveRoleMenu(Map<String, Object> map) throws CustomException;

}
