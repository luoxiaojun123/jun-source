package com.xiaojun.cache.jedis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

/**
 * redis操作客户端
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月9日
 */
@Repository("redisClientTemplate")
public class RedisClientTemplate {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private JedisDataSource jedisDataSource;

	public void disconnect() {
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		shardedJedis.disconnect();
	}

	/**
	 * 设置单个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		logger.info("设置的key和value" + key + ":" + value);
		String result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}

	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		logger.info("需要获取的值" + key);
		String result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.get(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}

	/**
	 * 判断key是否存在
	 */
	public Boolean exists(String key) {
		Boolean result = false;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}

	/**
	 * 设置key在某段时间后失效
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Long result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}

	/**
	 * 设置某个key在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireAt(String key, long unixTime) {
		Long result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.expireAt(key, unixTime);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}

	/**
	 * 获取某个key到期的剩余时间(秒)
	 * 
	 * @param key
	 * @return
	 */
	public Long ttl(String key) {
		Long result = null;
		ShardedJedis shardedJedis = jedisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.ttl(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(shardedJedis);
		}
		return result;
	}
}
