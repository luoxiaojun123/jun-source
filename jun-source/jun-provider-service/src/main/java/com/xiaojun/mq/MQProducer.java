package com.xiaojun.mq;

/**
 * mq消息发送者
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年2月14日
 */
public interface MQProducer {

	void sendDataToQueue(String queueKey, Object object);
}
