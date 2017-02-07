package com.xiaojun.dto;
/**
 * 角色dto
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月7日
 */
public class RoleDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5440291411359250651L;
	/**
	 * 角色名称
	 */
	private String roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
