package com.mine.library.demo.core.util.MailUtils;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MessageRec {
	private MimeMessage message=null;//构造此对象时的邮件参数message
	private String addsFrom=null;//发件人地址
	private String nameFrom=null;//发件人名称
	private List<AddressJSON> addressJSONTO=new ArrayList<AddressJSON>();
	private List<AddressJSON> addressJSONCC=new ArrayList<AddressJSON>();
	private List<AddressJSON> addressJSONBCC=new ArrayList<AddressJSON>();
	private String subject=null;//主题
	private StringBuffer bodyText=new StringBuffer();//内容
	private Integer attachState=0;//包含附件状态
	private String[] fileNames=null;
	private String[] filePaths=null;
	private List<String> fileNameList=new ArrayList<String>();
	private List<InputStream> fileList=new ArrayList<InputStream>();
	private String pattern="yyyy-MM-dd HH:mm";//默认的日期显示格式
	private SimpleDateFormat format=new SimpleDateFormat(pattern);
	private String sentDate;//发送日期
	private String receivedDate;//收件日期
	private String seen;
	/**
	 * 构造方法
	 * @param message
	 */
	public MessageRec(MimeMessage message){
		this.message = message;
	}
	/**
	 * 邮件简要解析(发件人名称、发件人地址、主题、发件日期、是否包含附件)
	 */
	public void analyseBrief(){
		analyseFrom();//发件人信息解析
		analyseSubject();//主题解析
		analyseSentDate();//发件日期解析
		analyseReceivedDate();//收件日期接卸
	}
	/**
	 * 邮件详细解析(收件人、抄送人、密抄人、内容、包含附件的要获取附件信息)
	 */
	public void analyseDetail(){
		analyseMailAddress("TO");
		analyseMailAddress("CC");
		analyseMailAddress("BCC");
		analyseMailContent(message);
	}
	/**
	 * 解析发件人信息
	 */
	private  void analyseFrom(){
		try {
			/*获取发件人信息*/
			InternetAddress[] address=(InternetAddress[]) message.getFrom();
			if(address!=null){
				addsFrom=address[0].getAddress()==null?"":address[0].getAddress();
				nameFrom=address[0].getPersonal()==null?"":address[0].getPersonal();
			}
		} catch (MessagingException e) {
			System.out.println("发件人地址解析错误……");
			e.printStackTrace();
		}
	}
	/**
	 * 依据类型解析地址信息
	 * @param type Address 类型 TO/CC/BCC
	 */
	private  void analyseMailAddress(String type){
		try {
			InternetAddress[] addresss=null;
			String recipientType=type.toUpperCase();
			if(recipientType.equals("TO")){//收件人
				addresss=(InternetAddress[]) message.getRecipients(Message.RecipientType.TO);
			}else if(recipientType.equals("CC")){//抄送人
				addresss=(InternetAddress[]) message.getRecipients(Message.RecipientType.CC);
			}else if(recipientType.equals("BCC")){//密抄人
				addresss=(InternetAddress[]) message.getRecipients(Message.RecipientType.BCC);
			}
			if(addresss!=null){
				for(int i=0;i<addresss.length;i++){
					InternetAddress address=addresss[i];
					String adds=address.getAddress()==null?"":address.getAddress();
					String name=address.getPersonal()==null?"":address.getPersonal();
					if(recipientType.equals("TO")){
						AddressJSON addressJSON=new AddressJSON(adds, name);
						addressJSONTO.add(addressJSON);
					}else if(recipientType.equals("CC")){
						AddressJSON addressJSON=new AddressJSON(adds, name);
						addressJSONCC.add(addressJSON);
					}else if(recipientType.equals("BCC")){
						AddressJSON addressJSON=new AddressJSON(adds, name);
						addressJSONBCC.add(addressJSON);
					}
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析主题
	 */
	private  void analyseSubject(){
		try {
			/*获取邮件主题*/
			subject=message.getSubject()==null?"未知":message.getSubject();
		} catch (MessagingException e) {
			System.out.println("主题解析错误……");
			e.printStackTrace();
		}
	}
	/**
	 * 解析发件日期
	 */
	private  void analyseSentDate(){
		try {
			if(message.getSentDate()!=null){
				sentDate=format.format(message.getSentDate());
			}else{
				sentDate="";
			}
		} catch (MessagingException e) {
			System.out.println("解析发件日期错误……");
			e.printStackTrace();
		}
	}
	/**
	 * 解析收件日期
	 */
	private  void analyseReceivedDate(){
		try {
			if(message.getReceivedDate()!=null){
				receivedDate=format.format(message.getReceivedDate());
			}else{
				receivedDate="";
			}
		} catch (MessagingException e) {
			System.out.println("解析收件日期错误……");
			e.printStackTrace();
		}
	}
	/**
	 * 解析邮件内容
	 */
	private void analyseMailContent(Part part){
//		try {
//			if(part!=null){
//				if(part.isMimeType("text/html")){
//					bodyText.append(part.getContent());
//				}else if(part.getDisposition()!=null&&part.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)){
//					fileNameList.add(part.getFileName()==null?"未知":part.getFileName());
//					fileList.add(part.getInputStream());
//					attachState=1;
//				}else if(part.isMimeType("multipart/*")){
//					Multipart multipart=(Multipart) part.getContent();
//					for(int i=0;i<multipart.getCount();i++){
//						BodyPart bodyPart=multipart.getBodyPart(i);
//						analyseMailContent(bodyPart);
//					}
//				}
//			}
//		} catch (MessagingException e) {
//			System.out.println("解析邮件内容错误……");
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		try {
			if(part==null){
				System.out.println("Part is Null!!!!!!!!!!!!");
			}else if(part.getContentType()==null){
				System.out.println("part.getContentType() is Null!!!!!!!!!!!!");
			}
			String contentType=part.getContentType();
			System.out.println("内容类型：：："+contentType);
			int nameindex=contentType.indexOf("name");
			boolean conname=false;
			if(nameindex!=-1)
				conname=true;
			if((part.isMimeType("text/html")||part.isMimeType("text/plain"))&&!conname){
				bodyText.append((String)part.getContent());
			}else if(part.isMimeType("multipart/*")){
				Multipart multipart=(Multipart)part.getContent();
				int counts=multipart.getCount();
				for(int i=0;i<counts;i++){
					analyseMailContent(multipart.getBodyPart(i));
				}
			}else if(part.isMimeType("message/rfc822")){
				analyseMailContent((Part)part.getContent());
			}else{}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e){
			System.out.println("邮件内容解析错误……");
		}
	}

	//——————————————————————————————————————————————————————————————————————————————————————————————————————
	public  void test() throws MessagingException{
		Enumeration e = message.getAllHeaders();
		//message.setHeader( "Content-Type ", "text/plain; charset=UTF-8");
		System.out.println(message.getHeader("Content-Type"));
		System.out.println("\n----------------");
		StringBuffer sb=new StringBuffer();
		while (e.hasMoreElements()) {
			Header header = (Header) e.nextElement();
			System.out.println("header name: " + header.getName());
			System.out.println("header value: " + header.getValue());
			if (header.getName().equalsIgnoreCase("Subject"))
				sb.append(header.getValue());
		}
		System.out.println("+++++++++++" + sb);

	}
	//apache Mime4j测试
//	public void testMime4j(){
//		org.apache.james.mime4j.message.Message mime4jMessage=new org.apache.james.mime4j.message.Message();
//	}
	public MimeMessage getMessage() {
		return message;
	}
	public void setMessage(MimeMessage message) {
		this.message = message;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Integer getMessageNum() {
		return message.getMessageNumber();
	}
	public String getAddsFrom() {
		return addsFrom;
	}
	public String getNameFrom() {
		return nameFrom;
	}
	public String getSubject() {
		return subject;
	}
	public String getBodyText() {
		System.out.println("bodyText:::\n"+bodyText);
		int start=bodyText.lastIndexOf("</HEAD>")==-1?bodyText.lastIndexOf("</head>"):bodyText.lastIndexOf("</HEAD>");
		int end=bodyText.lastIndexOf("</HTML>")==-1?bodyText.lastIndexOf("</HTML>"):bodyText.lastIndexOf("</HTML>");
		String endBodyText=bodyText.toString();
		if(start!=-1&&end!=-1){
			endBodyText=bodyText.substring(start+7, end);
		}
		return endBodyText;
	}
	/**
	 * 邮件是否阅读过
	 * @return
	 */
	public String getSeen() {
		boolean seen=false;
		try {
			Flags flags = message.getFlags();
			Flag[] flag=flags.getSystemFlags();
			for(int j=0;j<flag.length;j++){
				if(flag[j]==Flag.SEEN){
					seen=true;
					break;
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return seen+"";
	}
	public String[] getFileNames() {
		return fileNames;
	}
	public String[] getFilePaths() {
		return filePaths;
	}
	public String getSentDate() {
		return sentDate;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public List<String> getFileNameList() {
		return fileNameList;
	}
	public Integer getAttachState() {
		return attachState;
	}
	public List<AddressJSON> getAddressJSONTO() {
		return addressJSONTO;
	}
	public List<AddressJSON> getAddressJSONCC() {
		return addressJSONCC;
	}
	public List<AddressJSON> getAddressJSONBCC() {
		return addressJSONBCC;
	}

}
