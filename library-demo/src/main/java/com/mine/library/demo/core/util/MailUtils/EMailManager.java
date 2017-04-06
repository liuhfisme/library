package com.mine.library.demo.core.util.MailUtils;

import com.mine.library.demo.core.util.ApplicationPath;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * MailManager 邮件收发
 *
 * @author Administrator
 *
 */
public class EMailManager {
	private MimeMessage message = null;// 邮件
	private String[] addsTO = null;// 收件人地址
	private String[] namesTO = null;// 收件人名称
	private String[] addsCC = null;// 抄送人地址
	private String[] namesCC = null;// 抄送人名称
	private String[] addsBCC = null;// 密抄人地址
	private String[] namesBCC = null;// 密抄人名称
	private StringBuffer bodyText = new StringBuffer();// 邮件内容
	private String AttachPath = "";// 附件下载存放路径
	private String pattern = "yyyy-MM-dd HH:mm";// 默认的日期显示格式

	/**
	 * 构造方法获取邮箱帐号下的邮件对象
	 *
	 * @param message
	 */
	public EMailManager(MimeMessage message){
		this.message = message;
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
	 *
	 * @return
	 */
	public int getMessageNum() {
		return message.getMessageNumber();
	}

	/**
	 * 获取发送人名称和邮箱地址信息
	 *
	 * @return 发件人邮箱地址信息
	 * @throws MessagingException
	 */
	public String getFrom() throws MessagingException {
		String fromInfo = "";
		InternetAddress[] address = (InternetAddress[]) message.getFrom();
		if (address != null) {
			String fromAddress = address[0].getAddress() == null ? ""
					: address[0].getAddress();
			String fromName = address[0].getPersonal() == null ? ""
					: address[0].getPersonal();
			fromInfo = fromName + "<" + fromAddress + ">";
		}
		return fromInfo;
	}

	/**
	 * 获取发件人邮箱地址
	 *
	 * @return
	 * @throws MessagingException
	 */
	public String getFromAddress() throws MessagingException {
		String fromAddress = "";
		InternetAddress[] address = (InternetAddress[]) message.getFrom();
		if (address != null) {
			fromAddress = address[0].getAddress() == null ? "" : address[0]
					.getAddress();
		}
		return fromAddress;
	}

	/**
	 * 获取发件人昵称
	 *
	 * @return
	 * @throws MessagingException
	 */
	public String getFromName() throws MessagingException {
		String fromName = "";
		InternetAddress[] address = (InternetAddress[]) message.getFrom();
		if (address != null) {
			fromName = address[0].getPersonal() == null ? "" : address[0]
					.getPersonal();
		}
		return fromName;
	}

	/**
	 * 获取邮件主题
	 *
	 * @return 邮件主题
	 * @throws MessagingException
	 */
	public String getSubject() throws MessagingException {
		String subject = message.getSubject() == null ? "" : message
				.getSubject();
		return subject;
	}

	/**
	 * 获取邮件发送日期
	 *
	 * @return 邮件发送日期
	 */
	public Date getSentDate() {
		Date date = null;
		try {
			date = message.getSentDate();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取邮件接收时间
	 *
	 * @return 邮件接收日期
	 */
	public Date getReceiveDate() {
		Date date = null;
		try {
			date = message.getReceivedDate();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		;
		return date;
	}

	/**
	 * 解析收件人/抄送人/密抄人信息
	 */
	public void analyseMailAddress() {
		this.analyseMailAddress("TO");
		this.analyseMailAddress("CC");
		this.analyseMailAddress("BCC");
	}

	/**
	 * 依据类型解析地址信息
	 *
	 * @param type
	 *            Address 类型 TO/CC/BCC
	 */
	private void analyseMailAddress(String type) {
		try {
			InternetAddress[] addresss = null;
			String recipientType = type.toUpperCase();
			if (recipientType.equals("TO")) {
				addresss = (InternetAddress[]) message
						.getRecipients(Message.RecipientType.TO);
			} else if (recipientType.equals("CC")) {
				addresss = (InternetAddress[]) message
						.getRecipients(Message.RecipientType.CC);
			} else if (recipientType.equals("BCC")) {
				addresss = (InternetAddress[]) message
						.getRecipients(Message.RecipientType.BCC);
			}
			if (addresss != null) {
				for (int i = 0; i < addresss.length; i++) {
					InternetAddress address = addresss[i];
					String adds = address.getAddress() == null ? "" : address
							.getAddress();
					String name = address.getPersonal() == null ? "" : address
							.getPersonal();
					if (recipientType.equals("TO")) {
						addsTO[i] = adds;
						namesTO[i] = name;
					} else if (recipientType.equals("CC")) {
						addsCC[i] = adds;
						namesCC[i] = name;
					} else if (recipientType.equals("BCC")) {
						addsBCC[i] = adds;
						namesBCC[i] = name;
					}
				}
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析邮件内容信息
	 *
	 * @param part
	 */
	public void analyseMailContent(Part part) {
		try {
			if (part.isMimeType("text/html")) {// 带有格式类型 纯文本类型为 "text/plain"
				bodyText.append(part.getContent().toString());
			} else if (part.getDisposition() != null
					&& part.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {// 判断是否包含附件
				InputStream in = part.getInputStream();
				String dstPath = "E:\\TTTTTTT\\" + part.getFileName();
				copy(in, dstPath);
			} else if (part.isMimeType("multipart/*")) {// "alternative",
				// "mixed", "related",
				// "parallel", "signed"等
				Multipart multipart = (Multipart) part.getContent();
				for (int i = 0; i < multipart.getCount(); i++) {
					BodyPart bodyPart = multipart.getBodyPart(i);
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
	 *
	 * @param srcPath
	 *            传入要复制的源文件路径
	 * @param dstPath
	 *            传出复制文件路径
	 */
	public static void copy(InputStream in, String dstPath) {
		File dst = new File(dstPath);
		OutputStream out = null;
		try {
			File file1 = new File(dstPath.substring(0,
					dstPath.lastIndexOf("\\")));
			if (!file1.exists()) {
				file1.mkdirs();
			}
			out = new BufferedOutputStream(new FileOutputStream(dst));
			byte[] buffer;
			if (in.available() <= 1024) {
				buffer = new byte[1024];
			} else {
				buffer = new byte[in.available()];
			}
			while (in.read(buffer) > 0) {
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
	 *
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void getMailContent(Part part) throws MessagingException,
			IOException {
		String contentType = part.getContentType();
		int nameIndex = contentType.indexOf("name");
		boolean nameIndexFlag = false;
		if (nameIndex != -1)
			nameIndexFlag = true;
		if (part.isMimeType("text/html")) {
			bodyText.append(part.getContent().toString());
		} else if (part.isMimeType("multipart/*")) {
			// System.out.println("-//////////////////////////////////-");
			Multipart multipart = (Multipart) part.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		} else if (part.isMimeType("message/rfc822")) {
			getMailContent((Part) part.getContent());
		}

	}

	/**
	 * 获取邮件是否需要回执
	 *
	 * @return
	 */
	public boolean getReplySign() {
		boolean replaysign = false;
		try {
			String[] replayInfo = message
					.getHeader("Disposition-Notification-To");
			replaysign = replayInfo != null ? true : false;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return replaysign;
	}

	/**
	 * 判断邮件是否为新邮件 是返回true 否返回false
	 *
	 * @return
	 */
	public boolean isNew() {
		boolean isnew = false;
		try {
			Flags flags = message.getFlags();
			Flag[] flag = flags.getSystemFlags();
			for (int i = 0; i < flag.length; i++) {
				if (flag[i] == Flag.SEEN) {
					isnew = true;
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
	 *
	 * @param part
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public boolean containAttach(Part part) throws MessagingException,
			IOException {
		boolean containFlag = false;
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				String disposition = bodyPart.getDisposition();
				if (disposition != null && disposition.equals(Part.ATTACHMENT)) {
					// if(disposition!=null &&
					// (disposition.equals(Part.ATTACHMENT)||disposition.equals(Part.INLINE))){
					containFlag = true;
				} else if (bodyPart.isMimeType("multipart/*")) {
					containFlag = containAttach(bodyPart);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.toLowerCase().indexOf("application") != -1)
						containFlag = true;
					if (contentType.toLowerCase().indexOf("name") != -1)
						containFlag = true;
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			containFlag = containAttach((Part) part.getContent());
		}
		return containFlag;
	}
	/**
	 * 保存附件
	 *
	 * @param part
	 * @throws Exception
	 */
	public void saveAttach(Part part) throws Exception {
		if (containAttach(part)) {
			String fileName = "";
			Multipart multipart = (Multipart) part.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				String disposition = bodyPart.getDisposition();
				if (disposition != null && disposition.equals(Part.ATTACHMENT)) {
					System.out.println("===========================1"
							+ disposition);
					System.out.println("===========================2"
							+ disposition.equals(Part.ATTACHMENT));
					System.out.println("===========================3"
							+ disposition.equals(Part.INLINE));
					// if (disposition != null &&
					// (disposition.equals(Part.ATTACHMENT) ||
					// disposition.equals(Part.INLINE))) {
					fileName = bodyPart.getFileName();
					System.out.println("******************" + fileName);
					if (fileName.toLowerCase().indexOf("gb2312") != -1
							|| fileName.toLowerCase().indexOf("utf-8") != -1
							|| fileName.toLowerCase().indexOf("gbk") != -1)
						fileName = MimeUtility.decodeWord(fileName);
					// 保存附件
					saveFile(fileName, bodyPart.getInputStream());
				} else if (bodyPart.isMimeType("multipart/*")) {
					saveAttach(bodyPart);
				} else {
					fileName = bodyPart.getFileName();
					System.out.println("******************" + fileName);
					if (fileName != null
							&& (fileName.toLowerCase().indexOf("gb2312") != -1
							|| fileName.toLowerCase().indexOf("utf-8") != -1 || fileName
							.toLowerCase().indexOf("gbk") != -1)) {
						fileName = MimeUtility.decodeWord(fileName);
						// 保存附件
						saveFile(fileName, bodyPart.getInputStream());
					}
				}
			}
		}
	}

	/**
	 * 保存附件文件
	 */
	private void saveFile(String fileName, InputStream in) throws Exception {
		System.out.println("------------------------" + fileName);
		String storedst = getAttachPath();
		File storefile = new File(storedst + File.separator + fileName);
		System.out.println("------------------------" + storedst);
		System.out.println("------------------------" + storedst
				+ File.separator + fileName);
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
	 * 获取Folder[] 数组(通过IMAP服务器)
	 * @return
	 */
	public static Folder[] getFolder_IMAP(String server,String username,String password){
		Store store=null;
		Folder[] folderArr=null;
		Properties props=new Properties();
		props.put("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", server);
		Session session=Session.getDefaultInstance(props);
		try{
			store=session.getStore();
			store.connect(server, username, password);
			if(store!=null&&store.isConnected()){
				Folder defaultFolder=store.getDefaultFolder();
				folderArr=defaultFolder.list();
			}
		}catch (MessagingException e) {
			e.printStackTrace();
		}

		return folderArr;
	}
	/**
	 * 获取Folder (通过IMAP服务器和FolderType)
	 * @param server
	 * @param username
	 * @param password
	 * @param name
	 * @return
	 */
	public static Folder getFolder_IMAP_BY_NAME(String server,String username,String password,String name){
		Store store=null;
		Folder folder=null;
		Properties props=new Properties();
		props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", server);
		Session session=Session.getDefaultInstance(props);
		try{
			store=session.getStore();
			store.connect(server, username, password);
			if(store!=null&&store.isConnected()){
				folder=store.getFolder(name);
				folder.open(Folder.READ_ONLY);
			}
		}catch (MessagingException e) {
			e.printStackTrace();
		}

		return folder;
	}

	// TODO 测试东西
	public static void test_163_imap(){
		Store store=null;
		Folder folder=null;
		Properties props=new Properties();
		props.put("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", "imap.163.com");
		Session session=Session.getDefaultInstance(props);
		try {
			store=session.getStore();
			store.connect("imap.163.com", "liuff2513@163.com", "liuhaifei6174");
			System.out.println(store.isConnected());
			if(store!=null&&store.isConnected()){
				Folder defaultFolder=store.getDefaultFolder();
				Folder[] folderArr=defaultFolder.list();
				for(int i=0;i<folderArr.length;i++){
					folder=folderArr[i];
					System.out.println(i+"==================================*"+folder.getFullName());
					System.out.println("***count****"+folder.getMessageCount());
					System.out.println("***unR****"+folder.getUnreadMessageCount());
					System.out.println("***new****"+folder.getNewMessageCount());
				}
			}
		}catch (MessagingException e) {

		}finally{
			try {
				if(folder.isOpen()){
					folder.close(true);
				}
				if(store.isConnected()){
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO 测试2
	public static void test_doqi_imap(){
		System.out.println("*************************************************************************");
		Store store=null;
		Folder folder=null;
		Properties props=new Properties();
		props.put("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", "mail.doqi.com.cn");
		Session session=Session.getDefaultInstance(props);
		try {
			store=session.getStore();
			store.connect("mail.doqi.com.cn", "feifei_liu@doqi.com.cn", "doqi8888");
			System.out.println(store.isConnected());
			if(store!=null&&store.isConnected()){
				folder =store.getFolder("Junk Items");
				folder.open(Folder.READ_WRITE);
				System.out.println("1212123");
				System.out.println(folder.getFullName());
				System.out.println(folder.getName()+"====="+folder.getMessageCount());
//				Folder defaultFolder=store.getDefaultFolder();
//				Folder[] folderArr=defaultFolder.list();
//				for(int i=0;i<folderArr.length;i++){
//					folder=folderArr[i];
//					System.out.println(i+"=================================="+folder.getName());
//					System.out.println("***count****"+folder.getMessageCount());
//					System.out.println("***unR****"+folder.getUnreadMessageCount());
//					System.out.println("***new****"+folder.getNewMessageCount());
//				}
			}
		}catch (MessagingException e) {

		}finally{
			try {
				if(folder.isOpen()){
					folder.close(true);
				}
				if(store.isConnected()){
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	public static void test_doqi_pop3(){
		System.out.println("*************************************************************************");
		Store store=null;
		Folder folder=null;
		Properties props=new Properties();
		props.put("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.host", "mail.doqi.com.cn");
		Session session=Session.getDefaultInstance(props);
		try {
			store=session.getStore();
			store.connect("mail.doqi.com.cn", "feifei_liu@doqi.com.cn", "doqi8888");
			System.out.println(store.isConnected());
			if(store!=null&&store.isConnected()){
				folder =store.getFolder("Drafts");
				System.out.println(folder.getFullName());
				System.out.println(folder.getName()+"====="+folder.getMessageCount());
//				Folder defaultFolder=store.getDefaultFolder();
//				Folder[] folderArr=defaultFolder.list();
//				for(int i=0;i<folderArr.length;i++){
//					folder=folderArr[i];
//					System.out.println(i+"=================================="+folder.getName());
//					System.out.println("***count****"+folder.getMessageCount());
//					System.out.println("***unR****"+folder.getUnreadMessageCount());
//					System.out.println("***new****"+folder.getNewMessageCount());
//				}
			}
		}catch (MessagingException e) {

		}finally{
			try {
				if(folder.isOpen()){
					folder.close(true);
				}
				if(store.isConnected()){
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	public static String AbsoutleFilePathLog() {
		String rootPath = null;
		rootPath = ApplicationPath.getRootPath().replaceAll("\\\\", "\\\\\\\\");
		String AbsoutleFilePath = rootPath + "EmailLog" + File.separator;
		File file=new File(rootPath + "EmailLog");
		if(!file.exists()){
			file.mkdirs();
		}
		return AbsoutleFilePath;
	}
	/**
	 * 日志write
	 * @param str
	 * @param dstName
	 */
	public static void rmessageRecLog(String str,String dstName){
		FileWriter out=null;
		try {
			out=new FileWriter((AbsoutleFilePathLog()+dstName+".log"),true);
			out.write("\r\n");
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeEMailReceive(String str) {
		FileWriter out = null;
		try {
			out = new FileWriter(AbsoutleFilePathLog()
					+ "ReceiveEMailINFOdoqi.log", true);
			out.write("\r\n");
			out.write(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * PraseMimeMessage类测试
	 *
	 * @throws Exception
	 */
	public static void receiveTest(Folder folder) {
		try {
			System.out.println("是否有新邮件：：：" + folder.hasNewMessages());
			System.out.println("没有读取的邮件数量：：：" + folder.getUnreadMessageCount());
			System.out.println("邮件数量：：：" + folder.getMessageCount());
			System.out.println("新邮件数量" + folder.getNewMessageCount());
			System.out.println(folder.getFullName() + "--~");
			System.out.println("是否订阅：：：" + folder.isSubscribed());
			if (folder.hasNewMessages()) {
				System.out.println(folder.getNewMessageCount());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * PraseMimeMessage类测试
	 *
	 * @throws Exception
	 */
	public static void receiveTest() {
		System.out.println("work………………");
		Store store=null;
		Folder folder=null;
		Properties props=new Properties();
		props.put("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.host", "pop3.163.com");
		Session session=Session.getDefaultInstance(props);
		try {
			store=session.getStore();
			store.connect("pop3.163.com", "liuff2513@163.com", "liuhaifei6174");
			System.out.println(store.isConnected());
			if(store!=null&&store.isConnected()){
//				folder=store.getFolder("INBOX");
				Folder defaultFolder=store.getDefaultFolder();
				Folder[] folderArr=defaultFolder.list();
//				Folder[] folderArr=store.getPersonalNamespaces();
				for(int i=0;i<folderArr.length;i++){
					folder=folderArr[i];
					System.out.println(i+"===========");
					System.out.println("--name--"+folder.getName());
					System.out.println("--fullName--"+folder.getFullName());
				}
				System.out.println("newMessageCount1:::"+folder.getNewMessageCount());
				System.out.println("unreadMessageCount1:::"+folder.getUnreadMessageCount());
				folder.open(Folder.READ_WRITE);
				int messageCount=folder.getMessageCount();
				System.out.println("messageCount:::"+messageCount);
				System.out.println("newMessageCount2:::"+folder.getNewMessageCount());
				System.out.println("unreadMessageCount2:::"+folder.getUnreadMessageCount());
				for(int i=520;i<=messageCount;i++){
					Message message=folder.getMessage(i);
					InternetAddress[] fromAddress=(InternetAddress[]) message.getFrom();
//					Enumeration<Header> enume= message.getAllHeaders();
//					System.out.println(i+"---------------------------------------------");
//					while (enume.hasMoreElements()) {
//						Header header = (Header) enume.nextElement();
//						System.out.println("name--| "+header.getName());
//						System.out.println("value-| "+header.getValue()+"\n");
//					}
//					System.out.println(i+"---------------------------------------------");
//					System.out.println("--from--"+fromAddress[0].getAddress());
//					System.out.println("--fromName--"+fromAddress[0].getPersonal());
//					System.out.println("--subJect--"+message.getSubject());
//					System.out.println("--sentDate--"+message.getSentDate());
				}
			}
		}catch (MessagingException e) {

		}finally{
			try {
				if(folder.isOpen()){
					folder.close(true);
				}
				if(store.isConnected()){
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
}