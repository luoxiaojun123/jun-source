package com.xiaojun.shiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月13日
 */
import com.xiaojun.entity.SysUserEntity;
public class ShiroUtils {
	/**
	 * 获取shiro的session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	/**
	 * 获取主题
	 * @return
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	/**
	 * 从shiro中获取认证
	 * @return
	 */
	public static SysUserEntity getSysUserEntity(){
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}
	/**
	 * 获取用户id
	 * @return
	 */
	public static Integer getId() {
		return getSysUserEntity().getId();
	}
	/**
	 * 保存数据到session中
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}
	/**
	 * 获取session中的值
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	/**
	 * 判断用户是否登录认证过
	 * @return
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}
	/**
	 * 退出登录
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	/**
	 * 从session中获取验证码
	 * @param key
	 * @return
	 */
	public static String getKaptcha(String key){
		String kaptcha=getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
}
