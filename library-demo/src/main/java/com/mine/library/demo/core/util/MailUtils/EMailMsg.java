package com.mine.library.demo.core.util.MailUtils;
/**
 * 邮箱提示信息对象
 * @author feifei_liu 2013-12-11
 *
 */
public class EMailMsg {
	private String msg;//提示信息
	private String emailAddress;//邮箱地址
	private Integer emailId;//验证后的emailId
	private Integer verifyState;//0表示帐号密码错误 1表示验证成功
	public EMailMsg(){}
	public EMailMsg(String msg, String emailAddress, Integer emailId, Integer verifyState) {
		this.msg = msg;
		this.emailAddress=emailAddress;
		this.emailId = emailId;
		this.verifyState = verifyState;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Integer getEmailId() {
		return emailId;
	}
	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}
	public Integer getVerifyState() {
		return verifyState;
	}
	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}
}
