package com.xiaojun.controller.api;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaojun.controller.pay.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "测试swagger")
@Controller
@RequestMapping("/swagger")
public class SwaggerRestController extends BaseController {

	@ApiOperation(value = "测试", notes = "测试", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/testSwagger")
	@ResponseBody
	public String testSwagger(
			@ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
			@ApiParam(value = "用户姓名", required = false) @RequestParam(value = "userName", required = false) String userName) {
		return "luoxiaojun";
	}
}
