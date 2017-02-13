package com.xiaojun.cache.jedis;

import redis.clients.jedis.Jedis;

/**
 * Jedis数据源
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月13日
 */
public interface JedisDataSource {
	/**
	 * 获取redis客户端
	 * @return
	 */
	Jedis getRedisClient();
	/**
	 * 关闭jedis资源
	 * @param shardedJedis
	 */
	void returnResource(Jedis jedis);
	
}
