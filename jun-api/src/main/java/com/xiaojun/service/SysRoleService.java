package com.xiaojun.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.xiaojun.dto.RoleDTO;
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
	/**
	 * 获取分页
	 * @param dto
	 * @return
	 * @throws CustomException
	 */
	public PageInfo<SysRoleEntity> queryList(RoleDTO dto) throws CustomException;
	/**
	 * 获取角色信息 
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	public SysRoleEntity info(Integer id) throws CustomException;
	/**
	 * 根据id查询角色所包含权限
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	public List<Integer> queryMenuByRoleId(Integer id) throws CustomException;
	/**
	 * 保存
	 * @param role
	 * @return
	 * @throws CustomException
	 */
	public Integer save(SysRoleEntity role) throws CustomException;
	/**
	 * 更新
	 * @param role
	 * @return
	 * @throws CustomException
	 */
	public Integer update(SysRoleEntity role) throws CustomException;
	/**
	 * 删除
	 * @param ids
	 * @throws CustomException
	 */
	public void deleteBatch(Integer[] ids) throws CustomException;
	
	
}
