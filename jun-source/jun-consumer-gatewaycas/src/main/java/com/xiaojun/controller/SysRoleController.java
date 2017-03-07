package com.xiaojun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.xiaojun.dto.RoleDTO;
import com.xiaojun.entity.SysRoleEntity;
import com.xiaojun.service.SysRoleService;
import com.xiaojun.util.GSONUtils;
import com.xiaojun.util.Result;

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
	 * 
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

	/**
	 * 跳转角色列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public String list() {
		return "role";
	}

	/**
	 * 新增或者修改页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("roleAdd")
	public String roleAdd() {
		return "role_add";
	}
	/**
	 * 获取分页角色列表
	 * @param dto
	 * @return
	 */
	@RequestMapping("getPageRoleList")
	@ResponseBody
	public Map<String, Object> getPageRoleList(RoleDTO dto) {
		Map<String, Object> map = new HashMap<>();
		PageInfo<SysRoleEntity> pageInfo = sysRoleService.queryList(dto);
		map.put("pageInfo", pageInfo);
		return map;
	}

	/**
	 * 获取角色信息
	 * 
	 * @return
	 */
	@RequestMapping("info/{roleId}")
	@ResponseBody
	public Map<String, Object> info(@PathVariable("roleId") Integer roleId) {
		Map<String, Object> map = new HashMap<>();
		SysRoleEntity role = sysRoleService.info(roleId);
		map.put("role", role);
		return map;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(@RequestBody SysRoleEntity role) {
		Result<String> result = new Result<>();
		sysRoleService.save(role);
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestBody SysRoleEntity role) {
		Result<String> result = new Result<>();
		sysRoleService.update(role);
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestBody Integer[] ids) {
		Result<String> result = new Result<>();
		for (Integer id : ids) {
			if (id == 1) {
				result.error("管理员角色不能删除");
			}
		}
		sysRoleService.deleteBatch(ids);
		return GSONUtils.toJson(result, true);
	}
}
