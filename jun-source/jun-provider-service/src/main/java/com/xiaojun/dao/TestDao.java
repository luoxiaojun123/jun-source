package com.xiaojun.dao;

import com.xiaojun.annotation.SlaveDataSource;
import com.xiaojun.entity.TestEntity;

public interface TestDao extends BeseDao<TestEntity> {
	/**
	 * 根据主键获取姓名
	 * @param id
	 * @return
	 */
	@SlaveDataSource("dataSource")
	String queryNameById(Integer id);
}
