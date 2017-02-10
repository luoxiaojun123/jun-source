package com.xiaojun.cache.jedis;

import redis.clients.jedis.ShardedJedis;

/**
 * jedis数据源
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月9日
 */
public interface JedisDataSource {
	/**
	 * 获取客户端连接
	 * @return
	 */
	ShardedJedis getRedisClient();
	/**
	 * 回收资源
	 * @param shardedJedis
	 */
	void returnResource(ShardedJedis shardedJedis);
	
}
