package com.xiaojun.mq;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
public class TestQueue {

	@Autowired
	MQProducer mqProducer;

	@Test
	public void send() {
		Map<String, Object> msg = new HashMap<>();
		msg.put("data", "hello,rabbmitmq!");
		mqProducer.sendDataToQueue("test_queue_key", msg);
	}
}
