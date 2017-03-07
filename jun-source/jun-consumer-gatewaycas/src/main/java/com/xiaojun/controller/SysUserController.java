package com.xiaojun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.xiaojun.dto.UserDTO;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;
import com.xiaojun.service.SysUserService;
import com.xiaojun.service.cache.CacheService;
import com.xiaojun.service.mq.MessageProducerService;
import com.xiaojun.shiro.ShiroUtils;
import com.xiaojun.util.GSONUtils;
import com.xiaojun.util.PasswordHelper;
import com.xiaojun.util.Result;

/**
 * 用户相关Controller
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月17日
 */
@Controller
@RequestMapping("user")
public class SysUserController extends BaseController {
	/**
	 * 用户service
	 */
	@Reference
	private SysUserService sysUserService;
	@Reference
	private CacheService cacheService;
	@Reference
	private MessageProducerService MessageProducerService;

	/**
	 * 获取用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public String getUserInfo() throws CustomException {
		Result<SysUserEntity> result = new Result<>();
		SysUserEntity user = ShiroUtils.getSysUserEntity();
		// 测试spring 集成redis
		// String userInfo = cacheService.getUserInfo("12");
		// 测试spring 集成rabbitMQ
		//MessageProducerService.sendMessage("测试rabbitMQ");
		result.setResult(user);
		String resultJson = GSONUtils.toJson(result, true);
		logger.info("返回用户json" + resultJson);
		return resultJson;
	}

	/**
	 * 根据用户id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("info/{userId}")
	@ResponseBody
	public Map<String, Object> info(@PathVariable Integer userId) {
		SysUserEntity user = sysUserService.queryUser(userId);
		List<Integer> roleIdList = sysUserService.getRoleIdsByUserId(user.getId());
		user.setRoleIdList(roleIdList);
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		return map;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public String updatePassword(String oldPassword, String newPassword) throws CustomException {
		Result<String> result = new Result<>();
		if (StringUtils.isEmpty(oldPassword)) {
			result.error("旧密码不能为空");
			return GSONUtils.toJson(result, true);
		}
		// 旧密码加密后的结果
		oldPassword = PasswordHelper.encryptPassword(oldPassword);
		// 新密码加密后的结果
		newPassword = PasswordHelper.encryptPassword(newPassword);
		String username = ShiroUtils.getSysUserEntity().getUsername();
		Map<String, Object> map = new HashMap<>();
		map.put("oldPassword", oldPassword);
		map.put("newPassword", newPassword);
		map.put("username", username);
		sysUserService.updatePassword(map);
		ShiroUtils.logout();
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 跳转用户列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("list")
	public String list() {
		return "user";
	}

	/**
	 * 跳转 新增或者修改页面
	 * 
	 * @return
	 */
	@RequestMapping("userAdd")
	public String userAdd() {
		return "user_add";
	}

	/**
	 * 获取用户列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	@RequiresPermissions("sys:user:list")
	public Map<String, Object> getUserList(UserDTO dto) {
		PageInfo<SysUserEntity> pageInfo = sysUserService.queryList(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		return map;
	}

	/**
	 * 保存用户
	 * 
	 * @param SysUserEntity
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(@RequestBody SysUserEntity user) {
		Result<String> result = new Result<>();
		user.setPassword(PasswordHelper.encryptPassword(user.getPassword()));
		sysUserService.save(user);
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 更新用户
	 * 
	 * @param userRoleDTO
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestBody SysUserEntity user) {
		Result<String> result = new Result<>();
		user.setPassword(PasswordHelper.encryptPassword(user.getPassword()));
		sysUserService.update(user);
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 删除用户
	 * 
	 * @param userRoleDTO
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestBody Integer[] ids) {
		Result<String> result = new Result<>();
		if (ArrayUtils.contains(ids, 1)) {
			result.error("系统管理员不能删除");
			return GSONUtils.toJson(result, true);
		}
		if (ArrayUtils.contains(ids, ShiroUtils.getId())) {
			result.error("当前用户不能删除");
			return GSONUtils.toJson(result, true);
		}
		sysUserService.deleteBatch(ids);
		return GSONUtils.toJson(result, true);
	}

}
