package com.xiaojun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaojun.dao.SysMenuDao;
import com.xiaojun.dao.SysUserDao;
import com.xiaojun.dto.MenuDTO;
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

	@Override
	public PageInfo<SysMenuEntity> queryList(MenuDTO dto) throws CustomException {
		PageHelper.startPage(dto.getPage(), dto.getRows());
		PageHelper.orderBy("id desc");
		Map<String, Object> map = new HashMap<>();
		map.put("name", dto.getName());
		List<SysMenuEntity> list = sysMenuDao.queryList(map);
		PageInfo<SysMenuEntity> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() throws CustomException {
		List<SysMenuEntity> list = sysMenuDao.queryNotButtonList();
		// 添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setId(0);
		root.setName("一级菜单");
		root.setParentId(-1);
		root.setOpen(true);
		list.add(root);
		return list;
	}

	@Override
	public SysMenuEntity queryObject(Integer id) throws CustomException {
		return sysMenuDao.queryObject(id);
	}

	@Override
	public Integer deleteBatch(Integer[] menuIds) throws CustomException {
		return sysMenuDao.deleteBatch(menuIds);
	}

	@Override
	public Integer save(SysMenuEntity menu) throws CustomException {
		return sysMenuDao.save(menu);
	}

	@Override
	public void update(SysMenuEntity menu) throws CustomException {
		sysMenuDao.update(menu);
	}

	@Override
	public List<SysMenuEntity> perms() throws CustomException {
		return sysMenuDao.queryALlPermsList();
	}

}
