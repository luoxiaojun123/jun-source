package com.xiaojun.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaojun.service.mq.MessageProducerService;

/**
 * mq发送消息接口服务实现
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月15日
 */
@Service(interfaceName = "com.xiaojun.service.mq.MessageProducerService")
public class MessageProducerServiceImpl implements MessageProducerService {
	@Autowired
	private AmqpTemplate amqpTemplate;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void sendMessage(Object message) {
		logger.info("发送消息");
		logger.info("to send message:" + message);
		amqpTemplate.convertAndSend("pay_queue_key", message);
	}

}