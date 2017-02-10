package com.xiaojun.cache.jedis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Jedis 实现
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月9日
 */
@Repository("JedisDataSource")
public class JedisDataSourceImpl implements JedisDataSource {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	@Override
	public ShardedJedis getRedisClient() {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			logger.error("获取jedis数据源失败",e);
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return null;
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedis.close();
	}

}
