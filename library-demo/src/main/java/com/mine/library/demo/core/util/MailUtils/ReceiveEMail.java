package com.mine.library.demo.core.util.MailUtils;

import com.mine.library.demo.core.util.ApplicationPath;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;


/**
 * 收取邮件
 * @author feifei_liu 2013-12-25
 *
 */
public class  ReceiveEMail{
	private MimeMessage message=null;//邮件
	private String[] addsTO=null;//收件人地址
	private String[] namesTO=null;//收件人名称
	private String[] addsCC=null;//抄送人地址
	private String[] namesCC=null;//抄送人名称
	private String[] addsBCC=null;//密抄人地址
	private String[] namesBCC=null;//密抄人名称
	private StringBuffer bodyText = new StringBuffer();//邮件内容
	private String AttachPath="";//附件下载存放路径
	private String pattern="yyyy-MM-dd HH:mm";//默认的日期显示格式
	/**
	 * 构造方法获取邮箱帐号下的邮件对象
	 * @param message
	 */
	public ReceiveEMail(MimeMessage message){
		this.message=message;
	}
	public MimeMessage getMessage() {
		return message;
	}
	public void setMessage(MimeMessage message) {
		this.message = message;
	}
	public String getAttachPath() {
		return AttachPath;
	}
	public void setAttachPath(String attachPath) {
		AttachPath = attachPath;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getBodyText() {
		return bodyText.toString();
	}
	public void setBodytext(StringBuffer bodyText) {
		this.bodyText = bodyText;
	}
	public String[] getAddsTO() {
		return addsTO;
	}
	public String[] getNamesTO() {
		return namesTO;
	}
	public String[] getAddsCC() {
		return addsCC;
	}
	public String[] getNamesCC() {
		return namesCC;
	}
	public String[] getAddsBCC() {
		return addsBCC;
	}
	public String[] getNamesBCC() {
		return namesBCC;
	}
	/**
	 * 获取邮件的MessageNum
	 * @return
	 */
	public int getMessageNum(){
		return message.getMessageNumber();
	}
	/**
	 * 获取发送人名称和邮箱地址信息
	 * @return 发件人邮箱地址信息
	 * @throws MessagingException
	 */
	public String getFrom() throws MessagingException{
		String fromInfo="";
		InternetAddress[] address=(InternetAddress[])message.getFrom();
		if(address!=null){
			String fromAddress=address[0].getAddress()==null?"":address[0].getAddress();
			String fromName=address[0].getPersonal()==null?"":address[0].getPersonal();
			fromInfo=fromName+"<"+fromAddress+">";
		}
		return fromInfo;
	}
	/**
	 * 获取发件人邮箱地址
	 * @return
	 * @throws MessagingException
	 */
	public String getFromAddress() throws MessagingException{
		String fromAddress="";
		InternetAddress[] address=(InternetAddress[])message.getFrom();
		if(address!=null){
			fromAddress=address[0].getAddress()==null?"":address[0].getAddress();
		}
		return fromAddress;
	}
	/**
	 * 获取发件人昵称
	 * @return
	 * @throws MessagingException
	 */
	public String getFromName() throws MessagingException{
		String fromName="";
		InternetAddress[] address=(InternetAddress[])message.getFrom();
		if(address!=null){
			fromName=address[0].getPersonal()==null?"":address[0].getPersonal();
		}
		return fromName;
	}
	/**
	 * 获取邮件主题
	 * @return 邮件主题
	 * @throws MessagingException
	 */
	public String getSubject() throws MessagingException{
		String subject=message.getSubject()==null?"":message.getSubject();
		return subject;
	}
	/**
	 * 获取邮件发送日期
	 * @return 邮件发送日期
	 */
	public Date getSentDate(){
		Date date=null;
		try {
			date=message.getSentDate();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取邮件接收时间
	 * @return 邮件接收日期
	 */
	public Date getReceiveDate(){
		Date date=null;
		try {
			date= message.getReceivedDate();
		} catch (MessagingException e) {
			e.printStackTrace();
		};
		return date;
	}
	/**
	 * 解析收件人/抄送人/密抄人信息
	 */
	public void analyseMailAddress(){
		this.analyseMailAddress("TO");
		this.analyseMailAddress("CC");
		this.analyseMailAddress("BCC");
	}
	/**
	 * 依据类型解析地址信息
	 * @param type Address 类型 TO/CC/BCC
	 */
	public void analyseMailAddress(String type){
		try {
			InternetAddress[] addresss=null;
			String recipientType=type.toUpperCase();
			if(recipientType.equals("TO")){
				addresss=(InternetAddress[]) message.getRecipients(Message.RecipientType.TO);
			}else if(recipientType.equals("CC")){
				addresss=(InternetAddress[]) message.getRecipients(Message.RecipientType.CC);
			}else if(recipientType.equals("BCC")){
				addresss=(InternetAddress[]) message.getRecipients(Message.RecipientType.BCC);
			}
			if(addresss!=null){
				for(int i=0;i<addresss.length;i++){
					InternetAddress address=addresss[i];
					String adds=address.getAddress()==null?"":address.getAddress();
					String name=address.getPersonal()==null?"":address.getPersonal();
					if(recipientType.equals("TO")){
						addsTO[i]=adds;
						namesTO[i]=name;
					}else if(recipientType.equals("CC")){
						addsCC[i]=adds;
						namesCC[i]=name;
					}else if(recipientType.equals("BCC")){
						addsBCC[i]=adds;
						namesBCC[i]=name;
					}
				}
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析邮件内容信息
	 * @param part
	 */
	public void analyseMailContent(Part part){
		try {
			if(part.isMimeType("text/html")){//带有格式类型 纯文本类型为 "text/plain"
				bodyText.append(part.getContent().toString());
			}else if(part.getDisposition()!=null&&part.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)){//判断是否包含附件
				InputStream in=part.getInputStream();
				String dstPath="E:\\TTTTTTT\\"+part.getFileName();
				copy(in, dstPath);
			}else if(part.isMimeType("multipart/*")){//"alternative", "mixed", "related", "parallel", "signed"等
				Multipart multipart=(Multipart) part.getContent();
				for(int i=0;i<multipart.getCount();i++){
					BodyPart bodyPart=multipart.getBodyPart(i);
					analyseMailContent(bodyPart);
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 复制文件到指定目录
	 * @param srcPath 传入要复制的源文件路径
	 * @param dstPath 传出复制文件路径
	 */
	public static void copy(InputStream in,String dstPath){
		File dst=new File(dstPath);
		OutputStream out = null;
		try {
			File file1=new File(dstPath.substring(0, dstPath.lastIndexOf("\\")));
			if (!file1.exists()) {
				file1.mkdirs();
			}
			out=new BufferedOutputStream(new FileOutputStream(dst));
			byte[] buffer;
			if(in.available()<=1024){
				buffer=new byte[1024];
			}else{
				buffer=new byte[in.available()];
			}
			while(in.read(buffer)>0){
				out.write(buffer);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析邮件，获取邮件内容
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void getMailContent(Part part) throws MessagingException, IOException{
//		System.out.println(part.getSize());
//		System.out.println(part.getContent());
		String contentType=part.getContentType();
		int nameIndex=contentType.indexOf("name");
//		System.out.println(nameIndex+"***"+contentType);
		boolean nameIndexFlag=false;
		if(nameIndex!=-1)
			nameIndexFlag=true;
		if(part.isMimeType("text/html")){
			bodyText.append(part.getContent().toString());
		}else if(part.isMimeType("multipart/*")){
//			System.out.println("-//////////////////////////////////-");
			Multipart multipart=(Multipart) part.getContent();
			for(int i=0;i<multipart.getCount();i++){
				getMailContent(multipart.getBodyPart(i));
			}
		}else if(part.isMimeType("message/rfc822")){
			getMailContent((Part)part.getContent());
		}

	}
	//TODO 测试东西
	public  void test() throws MessagingException{
		Enumeration e = message.getAllHeaders();
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
	//TODO 测试2
	public void test2(Part part) throws MessagingException{
		Enumeration e = part.getAllHeaders();
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
	//TODO 测试3
	public void test3(){
		try {
			Address[] adds= message.getAllRecipients();
			for(int i=0;i<adds.length;i++){
				Address add=adds[i];
				System.out.println(add.getType()+"----"+add.toString());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取邮件是否需要回执
	 * @return
	 */
	public boolean getReplySign(){
		boolean replaysign = false;
		try {
			String[] replayInfo=message.getHeader("Disposition-Notification-To");
			replaysign=replayInfo!=null?true:false;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return replaysign;
	}
	/**
	 * 判断邮件是否为新邮件 是返回true 否返回false
	 * @return
	 */
	public boolean isNew(){
		boolean isnew=false;
		try {
			Flags flags=message.getFlags();
			Flag[] flag=flags.getSystemFlags();
			for(int i=0;i<flag.length;i++){
				if(flag[i]==Flag.SEEN){
					isnew=true;
					break;
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return isnew;
	}
	/**
	 * 判断邮件是否包含附件 是返回true 否返回false
	 * @param part
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public boolean containAttach(Part part) throws MessagingException, IOException{
		boolean containFlag=false;
		if(part.isMimeType("multipart/*")){
			Multipart multipart=(Multipart) part.getContent();
			for(int i=0;i<multipart.getCount();i++){
				BodyPart bodyPart=multipart.getBodyPart(i);
				String disposition=bodyPart.getDisposition();
				if(disposition!=null && disposition.equals(Part.ATTACHMENT)){
//				if(disposition!=null && (disposition.equals(Part.ATTACHMENT)||disposition.equals(Part.INLINE))){
					containFlag=true;
				}else if(bodyPart.isMimeType("multipart/*")){
					containFlag=containAttach(bodyPart);
				}else{
					String contentType=bodyPart.getContentType();
					if(contentType.toLowerCase().indexOf("application")!=-1)
						containFlag=true;
					if(contentType.toLowerCase().indexOf("name")!=-1)
						containFlag=true;
				}
			}
		}else if(part.isMimeType("message/rfc822")){
			containFlag=containAttach((Part)part.getContent());
		}
		return containFlag;
	}
//	public static boolean　isContainAttach(Part　part)throws　Exception{
//	　　boolean　attachflag　=　false;
//	　　String　contentType　=　part.getContentType();
//	　　if(part.isMimeType("multipart/*")){
//	　　　Multipart　mp　=　(Multipart)part.getContent();
//	　　　for(int　i=0;i<mp.getCount();i++){
//	　　　　BodyPart　mpart　=　mp.getBodyPart(i);
//	　　　　String　disposition　=　mpart.getDisposition();
//	　　　　if((disposition　!=　null)　&&((disposition.equals(Part.ATTACHMENT))　||(disposition.equals(Part.INLINE))))
//	　　　　　attachflag　=　true;
//	　　　　else　if(mpart.isMimeType("multipart/*")){
//	　　　　　attachflag　=　isContainAttach((Part)mpart);
//	　　　　}else{
//	　　　　　String　contype　=　mpart.getContentType();
//	　　　　　if(contype.toLowerCase().indexOf("application")　!=　-1)　attachflag=true;
//	　　　　　if(contype.toLowerCase().indexOf("name")　!=　-1)　attachflag=true;
//	　　　　}
//	　　　}
//	　　}else　if(part.isMimeType("message/rfc822")){
//	　　　attachflag　=　isContainAttach((Part)part.getContent());
//	　　}
//	　　return　attachflag;
//	}
	/**
	 * 保存附件
	 * @param part
	 * @throws Exception
	 */
	public void saveAttach(Part part) throws Exception{
		if(containAttach(part)){
			String fileName="";
			Multipart multipart=(Multipart) part.getContent();
			for(int i=0;i<multipart.getCount();i++){
				BodyPart bodyPart=multipart.getBodyPart(i);
				String disposition=bodyPart.getDisposition();
				if(disposition!=null && disposition.equals(Part.ATTACHMENT)){
					System.out.println("===========================1"+disposition);
					System.out.println("===========================2"+disposition.equals(Part.ATTACHMENT));
					System.out.println("===========================3"+ disposition.equals(Part.INLINE));
//				if (disposition != null && (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE))) {
					fileName=bodyPart.getFileName();
					System.out.println("******************"+fileName);
					if(fileName.toLowerCase().indexOf("gb2312")!=-1||fileName.toLowerCase().indexOf("utf-8")!=-1||fileName.toLowerCase().indexOf("gbk")!=-1)
						fileName=MimeUtility.decodeWord(fileName);
					//保存附件
					saveFile(fileName,bodyPart.getInputStream());
				}else if(bodyPart.isMimeType("multipart/*")){
					saveAttach(bodyPart);
				}else{
					fileName=bodyPart.getFileName();
					System.out.println("******************"+fileName);
					if (fileName != null  && (fileName.toLowerCase().indexOf("gb2312") != -1||fileName.toLowerCase().indexOf("utf-8")!=-1
							||fileName.toLowerCase().indexOf("gbk")!=-1)){
						fileName=MimeUtility.decodeWord(fileName);
						//保存附件
						saveFile(fileName,bodyPart.getInputStream());
					}
				}
			}
		}
	}
	/**
	 * 保存附件文件
	 */
	private void saveFile(String fileName, InputStream in) throws Exception {
		System.out.println("------------------------"+fileName);
		String storedst = getAttachPath();
		File storefile = new File(storedst + File.separator + fileName);
		System.out.println("------------------------"+storedst);
		System.out.println("------------------------"+storedst + File.separator + fileName);
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(storefile));
			bis = new BufferedInputStream(in);
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
				bos.flush();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception("文件保存失败!");
		} finally {
			bos.close();
			bis.close();
		}
	}

	/**
	 * PraseMimeMessage类测试
	 * @throws Exception
	 */
	public static void receiveTest(Folder folder){
		try {
			System.out.println("是否有新邮件：：："+folder.hasNewMessages());
			System.out.println("没有读取的邮件数量：：："+folder.getUnreadMessageCount());
			System.out.println("邮件数量：：："+folder.getMessageCount());
			System.out.println("新邮件数量"+folder.getNewMessageCount());
			System.out.println(folder.getFullName()+"--~");
			System.out.println("是否订阅：：："+folder.isSubscribed());
			if(folder.hasNewMessages()){
				System.out.println(folder.getNewMessageCount());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * PraseMimeMessage类测试
	 * @throws Exception
	 */
	public static void receiveTest(){
		String str="";
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "mail.doqi.com.cn");
			props.put("mail.smtp.auth", "true");
			Authenticator auth=new EMailValidation("feifei_liu@doqi.com.cn", "doqi8888");
			Session session = Session.getDefaultInstance(props, auth);
			URLName urln = new URLName("pop3", "mail.doqi.com.cn", 110, null,"feifei_liu@doqi.com.cn", "doqi8888");
			Store store = session.getStore(urln);
			store.connect();
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message message[] = folder.getMessages();
			System.out.println(folder.hasNewMessages());
			System.out.println(folder.getUnreadMessageCount());
			System.out.println(folder.getMessageCount());
			if(folder.hasNewMessages()){
				System.out.println(folder.getNewMessageCount());
			}
//	        System.out.println("Messages's length: " + message.length);
//	        ReceiveEMail pmm = null;
//	        for (int i = 0; i < message.length; i++) {
//	            System.out.println("======================");
//	            pmm = new ReceiveEMail((MimeMessage) message[i]);
//	            System.out.println("Message " + i + " subject: " + pmm.getSubject());
//	            System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());
//	            System.out.println("Message " + i + " replysign: "+ pmm.getReplySign());
//	            System.out.println("Message " + i + " hasRead: " + pmm.isNew());
//	            System.out.println("Message " + i + "  containAttachment: "+ pmm.containAttach((Part) message[i]));
//	            System.out.println("Message " + i + " form: " + pmm.getFromInfo());
//	            System.out.println("Message " + i + " to: "+ pmm.getMailAddress("to"));
//	            System.out.println("Message " + i + " cc: "+ pmm.getMailAddress("cc"));
//	            System.out.println("Message " + i + " bcc: "+ pmm.getMailAddress("bcc"));
//	            pmm.setDateFormat("yy年MM月dd日 HH:mm");
//	            System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());
//	            // 获得邮件内容===============
//	            pmm.getMailContent((Part) message[i]);   
//	            System.out.println("Message " + i + " bodycontent: \r\n"   
//	                    + pmm.getBodyText());   
//	            if(message[i]!=null){
////	            	pmm.setAttachPath("F:\\javaMail\\ReceiveMailAttch\\163");    
//	            	pmm.setAttachPath("F:\\javaMail\\ReceiveMailAttch\\doqi");    
//	            	pmm.saveAttach((Part) message[i]);    
//	            }
//	            str+="===================================================\n";
//	            str+="Message " + i + " subject: " + pmm.getSubject()+"\n";
//	            str+="Message " + i + " sentdate: "+ pmm.getSentDate()+"\n";
//	            str+="Message " + i + " replysign: "+ pmm.getReplySign()+"\n";
//	            str+="Message " + i + " hasRead: " + pmm.isNew()+"\n";
//	            str+="Message " + i + "  containAttachment: "+ pmm.containAttach((Part) message[i])+"\n";
//	            str+="Message " + i + " form: " + pmm.getFromInfo()+"\n";
//	            str+="Message " + i + " to: "+ pmm.getMailAddress("to")+"\n";
//	            str+="Message " + i + " cc: "+ pmm.getMailAddress("cc")+"\n";
//	            str+="Message " + i + " bcc: "+ pmm.getMailAddress("bcc")+"\n";
//	            str+="Message " + i + " sentdate: "+ pmm.getSentDate()+"\n";
//	        }
			writeEMailReceive(str);
		} catch (Exception e) {
			e.printStackTrace();
			writeEMailReceive(str);
		}
	}
	public static String AbsoutleFilePathLog() {
		String rootPath = null;
		rootPath = ApplicationPath.getRootPath().replaceAll("\\\\", "\\\\\\\\");
		String AbsoutleFilePath = rootPath + "EmailLog" + File.separator;
		return AbsoutleFilePath;
	}
	public static void writeEMailReceive(String str){
		FileWriter out=null;
		try {
//			out=new FileWriter(AbsoutleFilePathLog()+"ReceiveEMailINFO163.log",true);
			out=new FileWriter(AbsoutleFilePathLog()+"ReceiveEMailINFOdoqi.log",true);
			out.write("\r\n");
			out.write(str);
			out.flush();
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
