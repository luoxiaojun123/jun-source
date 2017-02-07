package com.xiaojun.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月4日
 */
public class SysRoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405993227370582056L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人
	 */
	private String create_name;
	/**
	 * 创建时间
	 */
	private String create_time;
	/**
	 * 更新人
	 */
	private String update_name;
	/**
	 * 更新时间
	 */
	private String update_time;
	/**
	 * 包含菜单
	 */
	private List<Integer> menuIdList;

	public List<Integer> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Integer> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreate_name() {
		return create_name;
	}

	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_name() {
		return update_name;
	}

	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
