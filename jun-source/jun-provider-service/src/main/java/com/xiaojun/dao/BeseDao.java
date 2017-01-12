package com.xiaojun.dao;

import java.util.List;
import java.util.Map;
/**
 * 基础Dao 需要对应每个xml都有相应的sql
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月10日
 * @param <T>
 */
public interface BeseDao<T> {

	int save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBath(List<T> list);
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] id);
	
	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
