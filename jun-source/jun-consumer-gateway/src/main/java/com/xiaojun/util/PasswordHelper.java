package com.xiaojun.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;



/**
 * 密码散列算法
 * 
 * @author xiaojun
 */
public class PasswordHelper {

	/**
	 * 散列算法
	 */
	private static final String ALGORITHMNAME = "md5";
	/**
	 * 散列次数
	 */
	private static final int HASHITERATIONS = 2;
	/**
	 * 盐
	 */
	private static final String SALT = "luoxiaojun20161227";

	/**
	 *  散列密码
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) {
		String newPassword = new SimpleHash(ALGORITHMNAME, password, ByteSource.Util.bytes(SALT), HASHITERATIONS)
				.toHex();
		return newPassword;
	}
	
	public static void main(String[] args) {
		DefaultPasswordEncoder defaultPasswordEncoder=new DefaultPasswordEncoder("MD5");
		System.out.println(PasswordHelper.encryptPassword("admin@2015"));
		String encryptedPassword = defaultPasswordEncoder.encode("admin@2015");
		System.out.println(encryptedPassword+"-----");
		Result<String> result=new Result<>();
		
		System.out.println(GSONUtils.toJson(result, true));
	}
}
