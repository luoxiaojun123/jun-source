package com.xiaojun.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
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
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月12日
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
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		//从token中获取用户名和密码
		String username=(String) token.getPrincipal();
		String password=new String((char[]) token.getCredentials());
		//从数据库里获取用户信息
		SysUserEntity user=sysUserService.selectSysUserByUserName(username);
		if (user==null) {
			throw new  UnknownAccountException("用户不存在");
		}
		
		
		
		return null;
	}

}
