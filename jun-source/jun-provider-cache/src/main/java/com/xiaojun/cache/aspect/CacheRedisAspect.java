package com.xiaojun.cache.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaojun.cache.jedis.RedisClientTemplate;
import com.xiaojun.util.GSONUtils;

/**
 * redis 切面
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月13日
 */
@Component
@Aspect
public class CacheRedisAspect {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private RedisClientTemplate redisClientTemplate;

	@Pointcut("execution(* com.xiaojun.cache.impl.CacheServiceImpl.*(..))")
	public void cacheRedisPointcut() {
	};

	@Around("cacheRedisPointcut()")
	public Object getCache(ProceedingJoinPoint point) {
		Object[] args = point.getArgs();
		String key = "";
		if (args != null && args.length != 0) {
			key = (String) args[0];
		}
		String value = "";
		try {
			value = redisClientTemplate.get(key);
		} catch (Exception e) {
			logger.warn("redis获取失败" + e.getMessage(), e);
			try {
				value = getResultByDB(point, args, key);
			} catch (Throwable e1) {
				logger.error("出错了" + e.getMessage(), e);
			}
		}
		if (StringUtils.isEmpty(value)) {
			try {
				return getResultByDB(point, args, key);
			} catch (Throwable e) {
				logger.error("出错了" + e.getMessage(), e);
			}
		}
		return value;

	}

	private String getResultByDB(ProceedingJoinPoint point, Object[] args, String key) throws Throwable {
		String value;
		// 执行目标方法
		Object proceed = point.proceed(args);
		// 将目标方式转化为json
		value = GSONUtils.toJson(proceed, false);
		// 保存进redis中
		redisClientTemplate.set(key, value);
		return value;
	}
}
