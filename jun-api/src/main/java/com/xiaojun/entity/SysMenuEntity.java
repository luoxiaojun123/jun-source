package com.xiaojun.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 系统菜单entity
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月19日
 */
public class SysMenuEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -590986583026103432L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 父id
	 */
	private Integer parentId;
	/**
	 * 父菜单名称
	 */
	private String parentName;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单url
	 */
	private String url;
	/**
	 * 权限
	 */
	private String perms;
	/**
	 * 类型  1：目录   2：菜单   3：按钮
	 */
	private int type;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 排序（数字越小越靠前）
	 */
	private String order_num;
	
	private String create_name;
	
	private String create_time;
	
	private String update_name;
	
	private String update_time;
	/**
	 * 子节点
	 */
	private List<?> list;
	
	/**
	 * ztree属性
	 */
	private Boolean open;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
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

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	
}
