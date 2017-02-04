package com.xiaojun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaojun.entity.SysMenuEntity;
/**
 * 菜单dao
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月19日
 */
import com.xiaojun.exception.CustomException;

public interface SysMenuDao extends BeseDao<SysMenuEntity> {
	/**
	 * 根据菜单查询所有子菜单
	 * 
	 * @param menuId
	 * @return
	 */
	public List<SysMenuEntity> queryChildListByMenuId(Integer menuId) throws CustomException;
	/**
	 * 根据用户id查询所有的权限
	 * @param userId
	 * @return
	 * @throws CustomException
	 */
	public List<String> queryAllPermsByUserId(@Param("userId") Integer userId) throws CustomException;
}
