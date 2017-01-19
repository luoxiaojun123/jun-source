package com.xiaojun.dao;

import java.util.List;

import com.xiaojun.entity.SysMenuEntity;
/**
 * 菜单dao
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月19日
 */
public interface SysMenuDao extends BeseDao<SysMenuEntity>  {
	/**
	 * 根据菜单查询所有子菜单
	 * @param menuId
	 * @return
	 */
	public List<SysMenuEntity> queryChildListByMenuId(Integer menuId);
}
