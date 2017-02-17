package com.xiaojun.cache.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

/**
 * redis操作客户端
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月13日
 */
@Repository("redisClientTemplate")
public class RedisClientTemplate {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JedisDataSource jedisDataSource;

	public void disconnect() {
		Jedis jedis = jedisDataSource.getRedisClient();
		jedis.disconnect();
	}

	/**
	 * 保存redis中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		logger.info("保存在redis中的key和value" + key + ":" + value);
		String result = null;
		Jedis jedis = jedisDataSource.getRedisClient();
		if (jedis == null) {
			return result;
		}
		try {
			result = jedis.set(key, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(jedis);
		}
		return result;
	}

	/**
	 * 根据key从缓存中获取ֵ
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) throws Exception {
		logger.info("获取缓存的key:" + key);
		String result = null;
		Jedis jedis = jedisDataSource.getRedisClient();
		if (jedis == null) {
			return result;
		}
		try {
			result = jedis.get(key);
		} finally {
			jedisDataSource.returnResource(jedis);
		}
		return result;
	}

	/**
	 * 判断key是否存在缓存中
	 */
	public Boolean exists(String key) {
		Boolean result = false;
		Jedis jedis = jedisDataSource.getRedisClient();
		if (jedis == null) {
			return result;
		}
		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(jedis);
		}
		return result;
	}

	/**
	 * 
	 * 设置key在缓存中的有效期
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Long result = null;
		Jedis jedis = jedisDataSource.getRedisClient();
		if (jedis == null) {
			return result;
		}
		try {
			result = jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedisDataSource.returnResource(jedis);
		}
		return result;
	}
}
