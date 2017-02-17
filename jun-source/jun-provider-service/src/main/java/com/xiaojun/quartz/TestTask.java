package com.xiaojun.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * 测试任务
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年2月17日
 */
public class TestTask extends QuartzJobBean {
	private Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		logger.info("quarzt定时任务测试");
	}

}
