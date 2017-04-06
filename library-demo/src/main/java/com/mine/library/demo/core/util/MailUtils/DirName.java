package com.mine.library.demo.core.util.MailUtils;
/**
 *邮件按日期分类
 * @author Administrator
 *
 */
public class DirName {
	private String name; //日期名称
	private Integer length;//包含邮件大小
	public DirName() {
		super();
	}
	public DirName(String name, Integer length) {
		super();
		this.name = name;
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
}