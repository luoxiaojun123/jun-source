package com.xiaojun.dto;

import java.io.Serializable;
/**
 * 支付所需参数
 * @author xiaojun
 */
public class OrderPayDTO implements Serializable {
	private static final long serialVersionUID = 6960183675390043498L;

	private String out_trade_no;
	
	private String total_amount;
	
	private String 	subject;
	
	private String seller_id;
	
	private String product_code;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	
}
