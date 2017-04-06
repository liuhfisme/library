package com.mine.library.demo.core.util.MailUtils;
/**
 * 地址信息
 * @author Administrator
 *
 */
public class AddressJSON {
	private String address;
	private String personal;

	public AddressJSON() {
		super();
	}

	public AddressJSON(String address, String personal) {
		super();
		this.address = address;
		this.personal = personal;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
}
