package com.xiaojun.service.cache;
/**
 * 缓存服务
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月10日
 */
public interface CacheService {
	/**
	 * 获取用户信息
	 * @param key
	 * @return
	 */
	String getUserInfo(String key);
}
