package com.xiaojun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaojun.dao.SysMenuDao;
import com.xiaojun.dao.SysUserDao;
import com.xiaojun.entity.SysMenuEntity;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;
import com.xiaojun.service.SysMenuService;

/**
 * 菜单service实现
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月19日
 */
@Service(interfaceName = "com.xiaojun.service.SysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<SysMenuEntity> getUserMenuList(SysUserEntity user) throws CustomException {
		// 根据用户查询目录
		List<SysMenuEntity> catalogList = sysUserDao.queryAllCatalogList(user.getId());
		if (catalogList == null) {
			return catalogList;
		}
		return getAllMenuList(catalogList);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<SysMenuEntity> catalogList) {
		List<SysMenuEntity> menuList = new ArrayList<>();
		for (SysMenuEntity catalog : catalogList) {
			List<SysMenuEntity> subEntity = sysMenuDao.queryChildListByMenuId(catalog.getId());
			catalog.setList(subEntity);
			menuList.add(catalog);
		}
		return menuList;
	}

	@Override
	public List<String> queryAllPermsByUserId(Integer userId) {
		return sysMenuDao.queryAllPermsByUserId(userId);
	}

}
