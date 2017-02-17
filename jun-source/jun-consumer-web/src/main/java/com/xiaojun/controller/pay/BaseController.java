package com.xiaojun.controller.pay;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础controller
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月19日
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Map<String, String> getNotifyParamsMap(HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Enumeration parameterNames = request.getParameterNames();
		Map<String, String> paramsMap = new HashMap<String, String>();
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValues.length != 0) {
					paramsMap.put(paramName, paramValue);
				}
			}
		}
		return paramsMap;
	}

}
