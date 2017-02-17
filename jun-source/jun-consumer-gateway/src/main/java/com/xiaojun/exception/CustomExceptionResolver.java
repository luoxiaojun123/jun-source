package com.xiaojun.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.xiaojun.util.GSONUtils;
import com.xiaojun.util.Result;

/**
 * spring 自定义全局拦截异常
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月17日
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
 
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Result<?> result = new Result<>();
		if (ex instanceof CustomException) {
			CustomException customException = (CustomException) ex;
			result.error(customException.getMsg());
			logger.error("业务异常",ex);
		} else {
			result.error("未知异常");
			logger.error("未知异常",ex);
		}
		try {
			response.getWriter().println(GSONUtils.toJson(result, true));
		} catch (Exception e) {
			logger.error("还是出错了",e);
		}
		return new ModelAndView();
	}

}
