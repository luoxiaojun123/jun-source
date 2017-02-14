package com.xiaojun.mq;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mq消息发送者实现
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月14日
 */
@Service
public class MQProducerImpl implements MQProducer {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void sendDataToQueue(String queueKey, Object object) {

		try {
			amqpTemplate.convertAndSend(queueKey, object);
		} catch (Exception e) {
			logger.error("发送消息出错了", e);
		}
	}
}
