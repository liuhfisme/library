package com.mine.library.demo.core.util.MailUtils;

public class MessageHeaderJSON {
	private Integer id;
	private String fromName;
	private String fromAddress;
	private String subject;
	private String date;
	private String seen;
	public MessageHeaderJSON() {
		super();
	}
	public MessageHeaderJSON(Integer id, String fromName, String fromAddress,
			String subject, String date) {
		super();
		this.id = id;
		this.fromName = fromName;
		this.fromAddress = fromAddress;
		this.subject = subject;
		this.date = date;
	}
	public MessageHeaderJSON(Integer id, String fromName, String fromAddress,
			String subject, String date, String seen) {
		super();
		this.id = id;
		this.fromName = fromName;
		this.fromAddress = fromAddress;
		this.subject = subject;
		this.date = date;
		this.seen = seen;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSeen() {
		return seen;
	}
	public void setSeen(String seen) {
		this.seen = seen;
	}
	
}
