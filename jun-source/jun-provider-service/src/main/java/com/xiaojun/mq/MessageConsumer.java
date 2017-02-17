package com.xiaojun.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * mq消费者
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月15日
 */
public class MessageConsumer implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onMessage(Message message) {
		logger.info("------消费者处理消息------");
		logger.info("receive message" + message);
	}

}
