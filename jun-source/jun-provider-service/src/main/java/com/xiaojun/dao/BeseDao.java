package com.xiaojun.dao;

import java.util.List;
import java.util.Map;
/**
 * 基础DAO 如果需要需在各个xml中写sql
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月18日
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
	
	int deleteBatch(Object[] ids);
	
	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
