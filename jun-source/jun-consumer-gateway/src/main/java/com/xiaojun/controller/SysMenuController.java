package com.xiaojun.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaojun.entity.SysMenuEntity;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.service.SysMenuService;
import com.xiaojun.shiro.ShiroUtils;
import com.xiaojun.util.GSONUtils;
import com.xiaojun.util.Result;

/**
 * 菜单相关Controller
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月19日
 */
@Controller
@RequestMapping("menu")
public class SysMenuController extends BaseController {

	@Reference
	private SysMenuService sysMenuService;

	@RequestMapping("/getMenuList")
	@ResponseBody
	public String getMenuList() {
		Result<List<SysMenuEntity>> result = new Result<>();
		SysUserEntity user = ShiroUtils.getSysUserEntity();
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(user);
		result.setResult(menuList);
		return GSONUtils.toJson(result, true);
	}
}
