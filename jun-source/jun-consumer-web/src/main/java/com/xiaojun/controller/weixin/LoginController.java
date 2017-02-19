package com.xiaojun.controller.weixin;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaojun.controller.pay.BaseController;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("WxAuth")
public class LoginController extends BaseController {
	@RequestMapping("login")
	public String login(){
		//回调地址
		String backUrl="http://luoxiaojun.tunnel.2bdata.com/WxAuth/callBack";
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AuthUtil.APPID
				+ "&redirect_uri="+URLEncoder.encode(backUrl)
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state=STATE#wechat_redirect";
		return "redirect:"+url;
	}
	@RequestMapping("callBack")
	public void callBack(HttpServletRequest request) throws Exception{
		String code=request.getParameter("code");
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AuthUtil.APPID
				+ "&secret="+AuthUtil.APPSECRET
				+ "&code="+code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject=AuthUtil.doGetJson(url);
		String openid=jsonObject.getString("openid");
		String access_token=jsonObject.getString("access_token");
		String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
				+ "&openid="+openid
				+ "&lang=zh_CN";
		JSONObject userInfo=AuthUtil.doGetJson(infoUrl);
		System.out.println(userInfo);
	}
}
