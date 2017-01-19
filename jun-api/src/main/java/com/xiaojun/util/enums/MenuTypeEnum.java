package com.xiaojun.util.enums;

public enum MenuTypeEnum {

	/**
	 * 类型  1：目录   2：菜单   3：按钮
	 */
	CATALOG(1), MENU(2), BUTTON(3);
	private int value;

	private MenuTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
