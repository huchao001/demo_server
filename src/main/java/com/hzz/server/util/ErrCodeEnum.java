package com.hzz.server.util;


public enum ErrCodeEnum {
	
	err_sms_01("E1001","你没有登录或登录失效!"),
	err_sms_failure("E1002","短信发送失败!"),
	err_sms_lose("E1003","验证码已过期,请重新获取!"),
	err_sms_wrong("E1004","请输入正确的验证码!"),
	err_sms_often("E1004","短信发送太频繁,请稍后再试!"),
	err_order_not_exist("E2001","订单不存在!"),
	err_order_not_pay("E2002","当前此订单不可支付，请前往订单中心查看订单状态"),
	err_order_amount("E2003","订单金额不一致!"),

	err_customer_sys("E9001","系统异常!"),
	err_zjtd_pay_comb("E9005","支付处理中，请继续通知"),

	err_server_01("E9001","暂时不能为你服务,请稍后再试")
	;

	private String code;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	ErrCodeEnum(String code, String msg){
		 this.code = code;
	     this.msg = msg;
	}

}
