package com.xiaojun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xiaojun.dto.MenuDTO;
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
	/**
	 * 根据用户id查询出所有权限
	 * @param userId
	 * @return
	 */
	public List<String> queryAllPermsByUserId(Integer userId) throws CustomException; 
	/**
	 * 获取菜单列表
	 * @param menuDTO
	 * @return
	 * @throws CustomException
	 */
	public PageInfo<SysMenuEntity> queryList(MenuDTO dto) throws CustomException;
	/**
	 * 获取没有按钮的目录和菜单
	 * @return
	 * @throws CustomException
	 */
	public List<SysMenuEntity> queryNotButtonList() throws CustomException;
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	public SysMenuEntity queryObject(Integer id) throws CustomException;
	/**
	 * 批量删除菜单
	 * @param menuIds
	 * @return
	 * @throws CustomException
	 */
	public Integer deleteBatch(Integer[] menuIds) throws CustomException;
	/**
	 * 保存
	 * @param menu
	 * @return
	 * @throws CustomException
	 */
	public Integer save(SysMenuEntity menu) throws CustomException;
	/**
	 * 更新
	 * @param menu
	 * @throws CustomException
	 */
	public void update(SysMenuEntity menu) throws CustomException;
	/**
	 * 权限查询
	 * @return
	 * @throws CustomException
	 */
	public List<SysMenuEntity> perms() throws CustomException;

}
