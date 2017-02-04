package com.xiaojun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaojun.entity.SysRoleEntity;
import com.xiaojun.service.SysRoleService;

/**
 * 角色相关controller
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月4日
 */
@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController {

	@Reference
	private SysRoleService sysRoleService;
	/**
	 * 获取角色集合
	 * @return
	 */
	@RequestMapping("getRoleList")
	@ResponseBody
	public Map<String, Object> getRoleList() {
		Map<String, Object> map = new HashMap<>();
		List<SysRoleEntity> list = sysRoleService.queryList(map);
		map.put("list", list);
		return map;
	}
}
