package com.mine.library.demo.core.util.MailUtils;

import java.util.Date;

/**
 * 邮件类Model
 * @author feifei_liu 2013-11-21
 *
 */
public class EMailMode {
	private String fromAddress;//发件人邮件地址
	private String fromName;//发件人昵称
	private String[] toSomeAddress;//收件人邮件地址
	private String[] toSomeName;//收件人昵称
	private String[] ccSomeAddress;//抄送人邮件地址
	private String[] ccSomeName;//抄送人昵称
	private String[] bccSomeAddress;//密抄人地址
	private String[] bccSomeName;//密抄人姓名
	private String subject;//邮件主题
	private String header;//邮件标题头信息
	private String body;//邮件内容
	private String host_server;//发送邮箱SMTP服务器
	private Date sendDate;//邮件发送时间
	private String fileStrs;//邮件附件地址
	public EMailMode() {
		super();
	}
	public EMailMode(String fromAddress, String fromName,
					 String[] toSomeAddress, String[] toSomeName,
					 String[] ccSomeAddress, String[] ccSomeName,
					 String[] bccSomeAddress, String[] bccSomeName, String subject,
					 String header, String body, String host_server, Date sendDate,
					 String fileStrs) {
		super();
		this.fromAddress = fromAddress;
		this.fromName = fromName;
		this.toSomeAddress = toSomeAddress;
		this.toSomeName = toSomeName;
		this.ccSomeAddress = ccSomeAddress;
		this.ccSomeName = ccSomeName;
		this.bccSomeAddress = bccSomeAddress;
		this.bccSomeName = bccSomeName;
		this.subject = subject;
		this.header = header;
		this.body = body;
		this.host_server = host_server;
		this.sendDate = sendDate;
		this.fileStrs = fileStrs;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String[] getToSomeAddress() {
		return toSomeAddress;
	}
	public void setToSomeAddress(String[] toSomeAddress) {
		this.toSomeAddress = toSomeAddress;
	}
	public String[] getToSomeName() {
		return toSomeName;
	}
	public void setToSomeName(String[] toSomeName) {
		this.toSomeName = toSomeName;
	}
	public String[] getCcSomeAddress() {
		return ccSomeAddress;
	}
	public void setCcSomeAddress(String[] ccSomeAddress) {
		this.ccSomeAddress = ccSomeAddress;
	}
	public String[] getCcSomeName() {
		return ccSomeName;
	}
	public void setCcSomeName(String[] ccSomeName) {
		this.ccSomeName = ccSomeName;
	}
	public String[] getBccSomeAddress() {
		return bccSomeAddress;
	}
	public void setBccSomeAddress(String[] bccSomeAddress) {
		this.bccSomeAddress = bccSomeAddress;
	}
	public String[] getBccSomeName() {
		return bccSomeName;
	}
	public void setBccSomeName(String[] bccSomeName) {
		this.bccSomeName = bccSomeName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHost_server() {
		return host_server;
	}
	public void setHost_server(String host_server) {
		this.host_server = host_server;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getFileStrs() {
		return fileStrs;
	}
	public void setFileStrs(String fileStrs) {
		this.fileStrs = fileStrs;
	}

}
