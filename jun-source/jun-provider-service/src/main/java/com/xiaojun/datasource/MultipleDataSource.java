package com.xiaojun.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 多数据源路由
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月11日
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	/**
	 * 为每个分配一个线程空间
	 */
	private static final ThreadLocal<String> dataSourceKey =new ThreadLocal<>(); 
	/**
	 * 设置数据源
	 * @param dataSource
	 */
	public static void setDataSourceKey(String dataSource){
		dataSourceKey.set(dataSource);
	}
	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceKey.get();
	}

}
