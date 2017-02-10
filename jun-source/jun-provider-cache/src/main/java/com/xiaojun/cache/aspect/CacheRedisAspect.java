package com.xiaojun.cache.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaojun.cache.jedis.RedisClientTemplate;
import com.xiaojun.util.GSONUtils;

/**
 * redis 缓存切面
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月10日
 */
@Component
@Aspect
public class CacheRedisAspect {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private RedisClientTemplate redisClientTemplate;

	@Pointcut("execution(* com.xiaojun.cache.*.*(..))")
	public void cacheRedisPointcut() {
	};

	public Object getCache(ProceedingJoinPoint point) {
		Object[] args = point.getArgs();
		String key = null;
		if (args != null && args.length != 0) {
			key = (String) args[0];
		}
		String value = null;
		try {
			value = redisClientTemplate.get(key);
		} catch (Exception e) {
			logger.warn("redis获取缓存失败" + e.getMessage(), e);
			try {
				value = getResultByDB(point, args, key);
			} catch (Throwable e1) {
				logger.error("出错了" + e.getMessage(), e);
			}
		}
		if ("".equals(value)) {
			try {
				return getResultByDB(point, args, key);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return value;

	}

	private String getResultByDB(ProceedingJoinPoint point, Object[] args, String key) throws Throwable {
		String value;
		// 执行目标方法，从数据库中获取
		Object proceed = point.proceed(args);
		// 将结果转化为json格式
		value = GSONUtils.toJson(proceed, false);
		// 存入缓存
		redisClientTemplate.set(key, value);
		return value;
	}
}
