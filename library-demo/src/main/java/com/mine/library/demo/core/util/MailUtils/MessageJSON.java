package com.mine.library.demo.core.util.MailUtils;

public class MessageJSON {
	private String subject;
	private String content;
	private String smessageTo;
	private String smessageCc;
	private String smessageBcc;
	public MessageJSON() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageJSON(String subject, String content, String smessageTo,
			String smessageCc, String smessageBcc) {
		super();
		this.subject = subject;
		this.content = content;
		this.smessageTo = smessageTo;
		this.smessageCc = smessageCc;
		this.smessageBcc = smessageBcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSmessageTo() {
		return smessageTo;
	}
	public void setSmessageTo(String smessageTo) {
		this.smessageTo = smessageTo;
	}
	public String getSmessageCc() {
		return smessageCc;
	}
	public void setSmessageCc(String smessageCc) {
		this.smessageCc = smessageCc;
	}
	public String getSmessageBcc() {
		return smessageBcc;
	}
	public void setSmessageBcc(String smessageBcc) {
		this.smessageBcc = smessageBcc;
	}
}
