package com.xiaojun.mongodb;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public void insert(User object, String collectionName) {
		mongoTemplate.insert(object, collectionName);
	}

	@Override
	public User findOne(Map<String, Object> params, String collectionName) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(params.get("id"))), User.class, collectionName);
	}

	@Override
	public List<User> findAll(Map<String, Object> params, String collectionName) {
		List<User> result = mongoTemplate.find(new Query(Criteria.where("age").lt(params.get("maxAge"))), User.class,
				collectionName);
		return result;
	}

	@Override
	public void update(Map<String, Object> params, String collectionName) {
		mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))),
				new Update().set("name", params.get("name")), User.class, collectionName);
	}

	@Override
	public void createCollection(String collectionName) {
		mongoTemplate.createCollection(collectionName);
	}

	@Override
	public void remove(Map<String, Object> params, String collectionName) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))), User.class, collectionName);
	}

}
