package com.xiaojun.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.exception.CustomException;
import com.xiaojun.service.SysUserService;
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
public class SysUserController {
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * 用户service
	 */
	@Reference
	private SysUserService sysUserService;

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
		result.setResult(user);
		String resultJson = GSONUtils.toJson(result, true);
		logger.info("返回用户json" + resultJson);
		return resultJson;
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
}
