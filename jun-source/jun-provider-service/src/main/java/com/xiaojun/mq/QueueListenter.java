package com.xiaojun.mq;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * mq消息监听消费
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月14日
 */
@Component(value="queueListenter")
public class QueueListenter implements MessageListener {

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(message.toString());
		} catch (Exception e) {
			logger.error("消费出错了", e);
		}
	}

}
