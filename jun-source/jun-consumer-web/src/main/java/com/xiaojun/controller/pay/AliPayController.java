package com.xiaojun.controller.pay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.gson.Gson;
import com.xiaojun.dto.OrderPayDTO;


@Controller
@RequestMapping("/v1/pay")
public class AliPayController extends BaseController{

	@Autowired
	private AlipayClient alipayClient;
	private static final String orderNo = "26150320010101014";

	private static final String seller_id = "2088102169231140";

	private static final String app_id = "2016073100129729";

	private static final String total_amount = "0.01";
	/**
	 * 初始化支付信息
	 * @param Request
	 * @param response
	 * @param orderPayTO
	 */
	@RequestMapping("/initPayParams")
	public void initPayParams(HttpServletRequest Request, HttpServletResponse response, OrderPayDTO orderPayDTO) {
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setNotifyUrl("http://localhost.tunnel.2bdata.com/spring-pay/v1/pay/notifyUrl");
		alipayRequest.setReturnUrl("http://localhost.tunnel.2bdata.com/spring-pay/v1/pay/returnUrl");
		Gson gson = new Gson();
		orderPayDTO.setOut_trade_no(orderNo);
		orderPayDTO.setProduct_code("QUICK_WAP_PAY");
		orderPayDTO.setSeller_id("2088102169231140");
		orderPayDTO.setSubject("dingdan");
		orderPayDTO.setTotal_amount("0.01");
		String biz_params = gson.toJson(orderPayDTO);
		alipayRequest.setBizContent(biz_params);
		try {
			String form = alipayClient.pageExecute(alipayRequest).getBody();
			response.setContentType("text/html;charset=" + "utf-8");
			response.getWriter().write(form);
			response.getWriter().flush();
		} catch (Exception e) {
			logger.error("支付初始化出错了",e);
			logger.info("支付参数"+biz_params);
		}
	}
	/**
	 * 支付同步地址
	 * @param request
	 * @param response
	 * @param orderPayTO
	 * @return
	 */
	@RequestMapping("/returnUrl")
	public String returnUrl(HttpServletRequest request, HttpServletResponse response) {
	//	Map<String, String> paramsMap = getNotifyParamsMap(request);
		return "success";
	}
	/**
	 * 支付异步通知地址
	 * @param request
	 * @param response
	 * @param orderPayTO
	 */
	@RequestMapping("/notifyUrl")
	public void notifyUrl(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> paramsMap = getNotifyParamsMap(request);
		try {
			String out_trade_no = paramsMap.get("out_trade_no");
			String total_amount_ = paramsMap.get("total_amount");
			String seller_id_ = paramsMap.get("seller_id");
			String app_id_ = paramsMap.get("app_id");
			if (!orderNo.equals(out_trade_no) || !seller_id.equals(seller_id_) || !total_amount.equals(total_amount_)
					|| !app_id.equals(app_id_)) {
				response.getWriter().print("failure");
			} else {
				boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
						"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB",
						"utf-8");
				if (signVerified) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("failure");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 订单交易查询
	 */
	@RequestMapping("/tradeQuery")
	public void tradeQuery(){
		AlipayTradeQueryRequest request=new AlipayTradeQueryRequest();
		Map<String, String> map=new HashMap<String, String>();
		map.put("out_trade_no", "26150320010101013");
		map.put("trade_no", "2016122321001004630200287020");
		Gson gson=new Gson();
		String querParams=gson.toJson(map);
		request.setBizContent(querParams);
		try {
		AlipayTradeQueryResponse response=alipayClient.execute(request);
		String tradeStatus=response.getTradeStatus();
		String tradeNo=response.getTradeNo();
		String outTradeNo=response.getOutTradeNo();
		System.out.println(tradeStatus+"|"+tradeNo+"|"+outTradeNo+"|");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 退款
	 */
	@RequestMapping("/tradeRefund")
	public void tradeRefund(){
		AlipayTradeRefundRequest request=new AlipayTradeRefundRequest();
		Map<String, String> map=new HashMap<String, String>();
		map.put("out_trade_no", "26150320010101013");
		map.put("trade_no", "2016122321001004630200287020");
		//map.put("trade_no", "1000001");
		map.put("refund_amount", "0.01");
		Gson gson=new Gson();
		String querParams=gson.toJson(map);
		request.setBizContent(querParams);
		try {
			AlipayTradeRefundResponse response=alipayClient.execute(request);
			String msg=response.getMsg();
			String refund_fee=response.getRefundFee();
			System.out.println(refund_fee);
			System.out.println(response.isSuccess());
			System.out.println(msg);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 账单查询
	 */
	@RequestMapping("billQuery")
	public void billQuery(){
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		Map<String, String> map=new HashMap<String, String>();
		map.put("bill_type", "trade");
		map.put("bill_date", "2016-12-22");
		Gson gson=new Gson();
		String querParams=gson.toJson(map);
		request.setBizContent(querParams);
		try {
			AlipayDataDataserviceBillDownloadurlQueryResponse response=alipayClient.execute(request);
			String downLoadUrl=response.getBillDownloadUrl();
			System.out.println(response.getMsg());
			String filePath = "d:"+File.separator+"fund_bill_20160405.txt";
			URL url=null;
			HttpURLConnection httpUrlConnection = null;
			InputStream fis = null;
			FileOutputStream fos = null;
			try {
			    url = new URL(downLoadUrl);
			    httpUrlConnection = (HttpURLConnection) url.openConnection();
			    httpUrlConnection.setConnectTimeout(5 * 1000);
			    httpUrlConnection.setDoInput(true);
			    httpUrlConnection.setDoOutput(true);
			    httpUrlConnection.setUseCaches(false);
			    httpUrlConnection.setRequestMethod("GET");
			    httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
			    httpUrlConnection.connect();
			    fis = httpUrlConnection.getInputStream();
			    byte[] temp = new byte[1024];
			    int b;
			    fos = new FileOutputStream(new File(filePath));
			    while ((b = fis.read(temp)) != -1) {
			        fos.write(temp, 0, b);
			        fos.flush();
			    }
			} catch (MalformedURLException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    try {
			        if(fis!=null) fis.close();
			        if(fos!=null) fos.close();
			        if(httpUrlConnection!=null) httpUrlConnection.disconnect();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
