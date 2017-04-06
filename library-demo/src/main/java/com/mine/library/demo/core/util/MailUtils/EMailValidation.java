package com.mine.library.demo.core.util.MailUtils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 邮箱服务器验证
 * @author feifei_liu 2013-11-21
 *
 */
public class EMailValidation extends Authenticator{
	private String username;
	private String password;
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(username, password);
	}
	public EMailValidation(){}
	public EMailValidation(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}