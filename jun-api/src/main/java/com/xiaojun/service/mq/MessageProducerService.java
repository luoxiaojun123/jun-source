package com.xiaojun.service.mq;
/**
 * mq发送消息接口服务
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月15日
 */
public interface MessageProducerService {
	/**
	 * 发送消息
	 */
	void sendMessage(Object message);
}
