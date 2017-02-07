package com.xiaojun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaojun.dao.SysRoleDao;
import com.xiaojun.dto.RoleDTO;
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

	@Override
	public PageInfo<SysRoleEntity> queryList(RoleDTO dto) throws CustomException {
		PageHelper.startPage(dto.getPage(), dto.getRows());
		PageHelper.orderBy("id desc");
		Map<String, Object> map = new HashMap<>();
		map.put("roleName", dto.getRoleName());
		List<SysRoleEntity> list = sysRoleDao.queryList(map);
		PageInfo<SysRoleEntity> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public SysRoleEntity info(Integer id) throws CustomException {
		SysRoleEntity role = sysRoleDao.queryObject(id);
		List<Integer> menuIdList = queryMenuByRoleId(id);
		role.setMenuIdList(menuIdList);
		return role;
	}

	@Override
	public List<Integer> queryMenuByRoleId(Integer id) throws CustomException {
		return sysRoleDao.queryMenuByRoleId(id);
	}

	@Override
	@Transactional
	public Integer save(SysRoleEntity role) throws CustomException {
		Integer roleId = sysRoleDao.save(role);
		if (role.getMenuIdList() != null && role.getMenuIdList().size() != 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("roleId", role.getId());
			map.put("menuIdList", role.getMenuIdList());
			sysRoleDao.saveRoleMenu(map);
		}
		return roleId;
	}

	@Override
	@Transactional
	public Integer update(SysRoleEntity role) throws CustomException {
		Integer id = sysRoleDao.update(role);
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", role.getId());
		// 删除角色菜单关联
		sysRoleDao.delete(map);
		if (role.getMenuIdList() != null && role.getMenuIdList().size() != 0) {
			map.put("menuIdList", role.getMenuIdList());
			// 保存角色菜单关联
			sysRoleDao.saveRoleMenu(map);
		}
		return id;
	}

	@Override
	public void deleteBatch(Integer[] ids) throws CustomException {
		sysRoleDao.deleteBatch(ids);
	}

}
