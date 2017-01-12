package com.xiaojun.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.xiaojun.annotation.SlaveDataSource;
import com.xiaojun.datasource.MultipleDataSource;

@Component
@Aspect
public class MultipleDataSourceAspectAdvice {

	@Pointcut("execution(* com.xiaojun.service.*.*(..))")
	public void multipleDataSource() {
	};

	@Around("multipleDataSource() && @annotation(dataSource)")
	public Object routeDateSource(ProceedingJoinPoint pjp,SlaveDataSource dataSource) throws Throwable {
		MultipleDataSource.setDataSourceKey(dataSource.value());
		Object name = pjp.proceed(pjp.getArgs());
		return name;
	}
}
