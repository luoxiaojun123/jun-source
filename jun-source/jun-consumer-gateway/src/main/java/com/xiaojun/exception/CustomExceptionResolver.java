package com.xiaojun.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.xiaojun.util.Result;
/**
 * spring 自定义全局拦截异常
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月17日
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Result<?> result=new Result<>();
		if (ex instanceof CustomException) {
			CustomException customException=(CustomException)ex;
			result.error(customException.getMsg());
		}else {
			result.error("未知异常");
		}
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.addObject("errorMessage", "错误");
		 modelAndView.setViewName("error");
		 ex.printStackTrace();
		 return modelAndView;
	}

}
