package com.xiaojun.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.service.SysUserService;

/**
 * 认证和授权realm
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月12日
 */
public class UserRealm extends AuthorizingRealm {

	@Reference
	private SysUserService sysUserService;

	private Cache<String, AtomicInteger> passwordRetryCache;

	public UserRealm(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中获取用户名和密码
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		// 从数据库里获取用户信息
		SysUserEntity user = sysUserService.selectSysUserByUserName(username);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		AtomicInteger retryCount = passwordRetryCache.get(user.getUsername());
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(user.getUsername(), retryCount);
		}
		// 自定义一个验证过程：当用户连续输入密码错误3次以上禁止用户登录一段时间
		if (retryCount.incrementAndGet() > 3) {
			throw new ExcessiveAttemptsException("密码输入错误超过3次,请过段时间重试");
		}
		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		passwordRetryCache.remove(user.getUsername());
		// 账号锁定
		if ("2".equals(user.getStatus())) {
			throw new LockedAccountException("账号已被禁用,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
