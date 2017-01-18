package com.xiaojun.controller;

import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * 获取用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public String getUserInfo() throws Exception {
		Result<SysUserEntity> result=new Result<>();
		SysUserEntity user = ShiroUtils.getSysUserEntity();
		result.setResult(user);
		String resultJson=GSONUtils.toJson(result, true);
		logger.info("返回用户json"+resultJson);
		return resultJson;
	}
}
