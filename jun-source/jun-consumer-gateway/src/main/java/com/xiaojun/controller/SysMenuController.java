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
import com.xiaojun.dto.MenuDTO;
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

	/**
	 * 获取用户相关菜单
	 * 
	 * @return
	 */
	@RequestMapping("/getMenuList")
	@ResponseBody
	public String getMenuList() {
		Result<List<SysMenuEntity>> result = new Result<>();
		SysUserEntity user = ShiroUtils.getSysUserEntity();
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(user);
		result.setResult(menuList);
		return GSONUtils.toJson(result, true);
	}

	@RequestMapping("list")
	public String list() {
		return "menu";
	}

	/**
	 * 跳转 新增或者修改页面
	 * 
	 * @return
	 */
	@RequestMapping("menuAdd")
	public String menuAdd() {
		return "menu_add";
	}

	/**
	 * 获取菜单列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("getAllMenuList")
	@ResponseBody
	public Map<String, Object> getAllMenuList(MenuDTO dto) {
		PageInfo<SysMenuEntity> pageInfo = sysMenuService.queryList(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		return map;
	}

	/**
	 * 获取菜单列表 不包含按钮
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("select")
	@ResponseBody
	public Map<String, Object> select() {
		Map<String, Object> map = new HashMap<>();
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		map.put("menuList", menuList);
		return map;
	}

	/**
	 * 获取菜单列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("info/{menuId}")
	@ResponseBody
	public Map<String, Object> info(@PathVariable("menuId") Integer menuId) {
		Map<String, Object> map = new HashMap<>();
		SysMenuEntity menu = sysMenuService.queryObject(menuId);
		map.put("menu", menu);
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestBody Integer[] menuIds) {
		Result<String> result = new Result<>();
		sysMenuService.deleteBatch(menuIds);
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 保存
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(@RequestBody SysMenuEntity menu) {
		Result<String> result = new Result<>();
		sysMenuService.save(menu);
		return GSONUtils.toJson(result, true);
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestBody SysMenuEntity menu) {
		Result<String> result = new Result<>();
		sysMenuService.update(menu);
		return GSONUtils.toJson(result, true);
	}
	/**
	 * 权限查询
	 * @return
	 */
	@RequestMapping("perms")
	@ResponseBody
	public Map<String, Object> perms() {
		Map<String, Object> map = new HashMap<>();
		List<SysMenuEntity> menuList = sysMenuService.perms();
		map.put("menuList", menuList);
		return map;
	}
}
