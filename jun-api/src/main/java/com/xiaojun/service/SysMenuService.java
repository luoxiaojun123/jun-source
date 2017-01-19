package com.xiaojun.service;

import java.util.List;

import com.xiaojun.entity.SysMenuEntity;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;



/**
 * 系统菜单
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月19日
 */
public interface SysMenuService {
	/**
	 * 查询出用户所有菜单
	 * @param user
	 * @return
	 */
	public List<SysMenuEntity> getUserMenuList(SysUserEntity user) throws CustomException;

}
