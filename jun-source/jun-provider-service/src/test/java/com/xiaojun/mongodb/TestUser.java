package com.xiaojun.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

	private static UserDao userDaoImpl;
	private static ClassPathXmlApplicationContext app;
	private static String collectionName;

	@BeforeClass
	public static void initSpring() {
		try {
			app = new ClassPathXmlApplicationContext(
					new String[] { "classpath:spring/spring-mongo.xml", "classpath:spring/spring-context.xml" });
			userDaoImpl = (UserDao) app.getBean("userDao");
			collectionName = "users";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		// 添加一百个user
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setId("" + i);
			user.setAge(i);
			user.setName("zcy" + i);
			user.setPassword("zcy" + i);
			userDaoImpl.insert(user, collectionName);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("maxAge", 50);
		List<User> list = userDaoImpl.findAll(params, collectionName);
		System.out.println("user.count()==" + list.size());
	}
	@Test
	public void testUpdate(){
		Map<String,Object> params=new HashMap<String,Object>();  
		params.put("id", "1");  
		User user=userDaoImpl.findOne(params, collectionName);  
		System.out.println("user.name==="+user.getName());  
		System.out.println("=============update==================");  
		params.put("name", "hello3");  
		userDaoImpl.update(params, collectionName);  
		 user=userDaoImpl.findOne(params, collectionName);  
		System.out.println("user.name==="+user.getName()); 
	}
	@Test
	public void testRemove(){
		Map<String,Object> params=new HashMap<String,Object>();  
		params.put("id", "2");  
		userDaoImpl.remove(params, collectionName);  
		User user=userDaoImpl.findOne(params, collectionName);  
		System.out.println("user=="+user);
	}

}
