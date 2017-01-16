package com.xiaojun.service.impl;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaojun.annotation.SlaveDataSource;
import com.xiaojun.dao.TestDao;
import com.xiaojun.service.DemoService;

@Service(interfaceName="com.xiaojun.service.DemoService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private TestDao testDao;

	@Override
	@SlaveDataSource("dataSource")
	@Transactional
	public String sayHello(Integer id) {
		return "" + testDao.queryNameById(id);
	}
	
	@Override
	@SlaveDataSource("dataSource_slave")
	@Transactional
	public String sayHello_salve(Integer id) {
		return "_salve" + testDao.queryNameById(id);
	}

	@Override
	public String result(Integer id) {
		// TODO Auto-generated method stub
		//一个方法中获取当前代理类
		DemoServiceImpl demoServiceImpl=(DemoServiceImpl) AopContext.currentProxy();
		return demoServiceImpl.sayHello(id)+"---------"+demoServiceImpl.sayHello_salve(id);
	}

}
