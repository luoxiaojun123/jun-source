package com.xiaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.shiro.ShiroUtils;
import com.xiaojun.util.GSONUtils;
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

	/**
	 * 获取用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public String getUserInfo() throws Exception {
		int a=1/0;
		Result<SysUserEntity> result=new Result<>();
		SysUserEntity user = ShiroUtils.getSysUserEntity();
		result.setResult(user);
		return GSONUtils.toJson(result, true);
	}
}
