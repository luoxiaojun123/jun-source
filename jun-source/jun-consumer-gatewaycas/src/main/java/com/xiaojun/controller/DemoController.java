package com.xiaojun.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaojun.service.DemoService;

@Controller
public class DemoController {
	@Reference
	private DemoService demoService;
	@RequestMapping("/test")
	public void test(HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		out.println(demoService.sayHello(1)+"------------"+demoService.sayHello_salve(1));
		out.println(demoService.result(1));
	}
}
