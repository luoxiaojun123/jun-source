package com.xiaojun.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.xiaojun.shiro.ShiroUtils;
import com.xiaojun.util.GSONUtils;
import com.xiaojun.util.PasswordHelper;
import com.xiaojun.util.Result;

/**
 * 登录相关
 * 
 * @author xiaojun
 * @email lxjluoxiaojun@163.com
 * @date 2017年1月16日
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	@Autowired
	private Producer producer;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 获取验证码
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 将验证码保存到shiro session中
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(String username, String password, String captcha) throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		Result<String> result = new Result<>();
		if (!captcha.equalsIgnoreCase(kaptcha)) {
			result.error("验证码错误");
			return GSONUtils.toJson(result, true);
		}
		try {
			Subject subject = ShiroUtils.getSubject();
			password = PasswordHelper.encryptPassword(password);
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			return GSONUtils.toJson(result.error(e.getMessage()), true);
		} catch (IncorrectCredentialsException e) {
			return GSONUtils.toJson(result.error(e.getMessage()), true);
		} catch (LockedAccountException e) {
			return GSONUtils.toJson(result.error(e.getMessage()), true);
		} catch (ExcessiveAttemptsException e) {
			return GSONUtils.toJson(result.error(e.getMessage()), true);
		} catch (AuthenticationException e) {
			return GSONUtils.toJson(result.error("账户验证失败"), true);
		}
		return GSONUtils.toJson(result, true);
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("main")
	public String main() {
		return "main";
	}

	@RequestMapping("logout")
	public String logout() {
		ShiroUtils.logout();
		return "login";
	}
}
