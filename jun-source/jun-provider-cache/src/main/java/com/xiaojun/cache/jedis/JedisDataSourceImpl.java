package com.xiaojun.cache.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Jedis数据源实现
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月13日
 */
@Repository("jedisDataSource")
public class JedisDataSourceImpl implements JedisDataSource {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JedisPool jedisPool;

	@Override
	public Jedis getRedisClient() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			logger.error("出错了", e);
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	@Override
	public void returnResource(Jedis jedis) {
		jedis.close();
	}

}
