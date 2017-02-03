package com.xiaojun.service;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.xiaojun.dto.UserDTO;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;
/**
 * 系统用户service
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月16日
 */
public interface SysUserService {
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	public SysUserEntity selectSysUserByUserName(String userName) throws CustomException;
	/**
	 * 根据用户名更新密码
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	public int updatePassword(Map<String, Object> map) throws CustomException;
	
	/**
	 * 查询用户集合
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	public PageInfo<SysUserEntity> queryList(UserDTO dto) throws CustomException;
	
}
