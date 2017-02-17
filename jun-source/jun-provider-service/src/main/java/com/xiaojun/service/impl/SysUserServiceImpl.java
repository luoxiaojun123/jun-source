package com.xiaojun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

	private Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUserEntity selectSysUserByUserName(String username) throws CustomException {
		logger.debug("查询用户信息根据：{}", username);
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
		PageHelper.orderBy("id desc");
		Map<String, Object> map = new HashMap<>();
		map.put("userName", dto.getUserName());
		List<SysUserEntity> list = sysUserDao.queryList(map);
		PageInfo<SysUserEntity> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Integer> getRoleIdsByUserId(Integer userId) throws CustomException {
		return sysUserDao.getRoleIdsByUserId(userId);
	}

	@Override
	public SysUserEntity queryUser(Integer userId) throws CustomException {
		return sysUserDao.queryObject(userId);
	}

	@Override
	@Transactional
	public Integer save(SysUserEntity user) throws CustomException {
		sysUserDao.save(user);
		Integer userId = user.getId();
		// 删除用户角色关系
		sysUserDao.delete(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", user.getRoleIdList());
		// 插入用户角色关系
		sysUserDao.saveUserRole(map);
		return userId;
	}

	@Override
	public Integer update(SysUserEntity user) throws CustomException {
		sysUserDao.update(user);
		Integer userId = user.getId();
		// 删除用户角色关系
		sysUserDao.delete(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", user.getRoleIdList());
		// 插入用户角色关系
		sysUserDao.saveUserRole(map);
		return userId;
	}

	@Override
	public void saveUserRole(Map<String, Object> map) throws CustomException {
		sysUserDao.saveUserRole(map);
	}

	@Override
	public Integer deleteBatch(Integer[] ids) throws CustomException {
		return sysUserDao.deleteBatch(ids);
	}

}
