package com.mine.library.demo.core.util.MailUtils;

import com.mine.library.demo.core.util.ApplicationPath;
import com.mine.library.demo.core.util.DateUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;


/**
 * 发送邮件工具类
 * @author feifei_liu 2013-11-21
 *
 */
public class EMailTool {
	public static String sendMail(EMailMode emm,EMailValidation emv){
		/*错误信息*/
		String strError;
		/*设置属性*/
		Properties props=new Properties();
		/*邮箱服务器验证*/
		Authenticator auth=new EMailValidation(emv.getUsername(), emv.getPassword());
		/*设置邮件发送协议*/
		props.setProperty("mail.transport.protocol", "smtp");
		/*设置邮件SMTP服务器*/
		props.setProperty("mail.smtp.host", emm.getHost_server());
		/*设置发送邮件是否进行登录验证*/
		props.setProperty("mail.smtp.auth", "true");
		/*定义邮件会话*/
		Session session=Session.getDefaultInstance(props, auth);
		/*定义一封邮件*/
		Message msg=new MimeMessage(session);
		/*获取邮件发送器*/
		try {
			/*设置邮件主题*/
			if(emm.getSubject()!=null){
				msg.setSubject(emm.getSubject());
			}
			/*设置邮件发送时间*/
			msg.setSentDate(emm.getSendDate());
			/*设置邮件主体内容*/
			BodyPart msgBodyPart=new MimeBodyPart();
			Multipart multipart=new MimeMultipart();
			if(emm.getBody()!=null){
				msgBodyPart.setContent(emm.getBody(),"text/html;charset=UTF-8");
				multipart.addBodyPart(msgBodyPart);
			}
			/*设置邮件附件地址和附件名称*/
			if(emm.getFileStrs()!=null){
				msgBodyPart=new MimeBodyPart();
				DataSource source=new FileDataSource(emm.getFileStrs());
				//添加附件file
				msgBodyPart.setDataHandler(new DataHandler(source));
				try {
					//设置附件名称
					msgBodyPart.setFileName(MimeUtility.encodeWord(emm.getFileStrs().substring(emm.getFileStrs().lastIndexOf("\\")+1)));
				} catch (UnsupportedEncodingException e) {
					strError="附件路径或文件格式异常！请检查确认！";
//					System.out.println("strError:::"+strError);
					return strError;
				}
				multipart.addBodyPart(msgBodyPart);
			}
			msg.setContent(multipart);
			/*设置邮件发件人地址*/
			Address address=null;
			try{
				address=new InternetAddress(emm.getFromAddress(), emm.getFromName()==null?"":emm.getFromName());
			}catch (UnsupportedEncodingException e) {
				strError="对发件人邮箱地址编码时异常！";
//				System.out.println("strError:::"+strError);
				return strError;
			}
			msg.setFrom(address);//发件人地址
			/*设置邮件收件人地址*/
			Address[] addressTO=new Address[emm.getToSomeAddress().length];//定义收件人地址字符数组
			for(int i=0;i<emm.getToSomeAddress().length;i++){
				try {
					addressTO[i]=new InternetAddress(emm.getToSomeAddress()[i], emm.getToSomeName()[i]==null?"":emm.getToSomeName()[i]);
				} catch (UnsupportedEncodingException e) {
					strError="对收件人邮箱地址编码时异常！";
//					System.out.println("strError:::"+strError);
					return strError;
				}
			}
			msg.setRecipients(Message.RecipientType.TO, addressTO);
			/*设置邮件抄送人地址*/
			if(emm.getCcSomeAddress()!=null){
				Address[] addressCC=new Address[emm.getCcSomeAddress().length];//定义抄送人地址字符数组
				for(int i=0;i<emm.getCcSomeAddress().length;i++){
					try {
						addressCC[i]=new InternetAddress(emm.getCcSomeAddress()[i],emm.getCcSomeName()[i]==null?"":emm.getCcSomeName()[i]);
					} catch (UnsupportedEncodingException e) {
						strError="对抄送人邮箱地址编码时异常！";
//						System.out.println("strError:::"+strError);
						return strError;
					}
				}
				msg.setRecipients(Message.RecipientType.CC, addressCC);
			}
			/*设置邮件密抄人地址*/
			if(emm.getBccSomeAddress()!=null){
				Address[] addressBcc=new Address[emm.getBccSomeAddress().length];//定义密抄人地址字符数组
				for(int i=0;i<emm.getBccSomeAddress().length;i++){
					try {
						addressBcc[i]=new InternetAddress(emm.getBccSomeAddress()[i], emm.getBccSomeName()[i]==null?"":emm.getBccSomeName()[i]);
					} catch (UnsupportedEncodingException e) {
						strError="对密抄人邮箱地址编码时异常！";
//						System.out.println("strError:::"+strError);
						return strError;
					}
				}
				msg.setRecipients(Message.RecipientType.BCC,addressBcc);
			}
			final Message msg2=msg;
			final EMailMode emm2=emm;
			new Thread(){
				public void run(){
					super.run();
					try {
						Transport.send(msg2);
						System.out.println("----发送成功----");
					} catch (MessagingException e) {
						System.out.println("----发送失败----");
						FileWriter out=null;
						try {
							out=new FileWriter(AbsoutleFilePathLog()+"EMailErrorLog.log",true);
							out.write("\r\n");
							out.write(DateUtil.getDateFull()+": to "+emm2.getToSomeAddress()+Arrays.toString(emm2.getToSomeAddress())+" has error");
							out.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}finally{
							try {
								out.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}


			}.start();
			strError = "发送成功";
			return strError;
		} catch (MessagingException e) {
			strError = "与邮件服务器通信时发生异常！请确认网络、SMTP服务器、收件人、发件人邮箱地址正确...";
//			System.out.println(strError);
			return strError;
		}
	}
	/**
	 * 错误日志地址
	 * @return
	 */
	public static String AbsoutleFilePathLog() {
		String rootPath = null;
		rootPath = ApplicationPath.getRootPath().replaceAll("\\\\", "\\\\\\\\");
		String AbsoutleFilePath = rootPath + "EmailLog" + File.separator;
		return AbsoutleFilePath;
	}
	/**
	 * 验证邮箱服务器是否连接上了
	 * @param type 收取邮件类型(pop3/imap)
	 * @param server 收取邮件服务器地址
	 * @param username 用户名address
	 * @param password 通行密码
	 * @return
	 */
	public static boolean connVerify(String type,String server,String username,String password){
		Store store=null;
		boolean connFlag=false;
		Properties props=new Properties();
		props.setProperty("mail.store.protocol", type);
		props.setProperty("mail.imap.host", server);
		Session session=Session.getDefaultInstance(props);
		try {
			store=session.getStore();
			store.connect(server, username, password);
			if(store!=null)
				connFlag=store.isConnected();
			System.out.println("服务器是否连接：：："+connFlag);
		} catch (MessagingException e) {
			System.out.println("用户名或密码解析错误！");
			e.printStackTrace();
		}finally{
			try {
				store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		return connFlag;
	}
	/**
	 * 获取Folder
	 * @param smtpServer
	 * @param type
	 * @param server
	 * @param username
	 * @param password
	 * @return
	 */
	public static Folder getFolder(String smtpServer,String type,String server,String username,String password){
		Folder folder=null;
		Store store=null;
		try {
			Properties props=new Properties();
			props.setProperty("mail.store.protocol", type);
//			props.setProperty("mail.pop3.host", server);
			props.setProperty("mail.imap.host", server);
			Session session=Session.getDefaultInstance(props);
			store=session.getStore();
			store.connect(server, username, password);
			if(store.isConnected()){
				folder=store.getFolder("INBOX");
				folder.open(Folder.READ_WRITE);
			}
		} catch (MessagingException e) {
			throw new IllegalArgumentException("连接服务器失败，请检查用户名和密码是否正确！");
		}
		return folder;
	}
	public static void sendTest(){
		EMailMode emm=new EMailMode("feifei_liu@doqi.com.cn", "北京多企管理顾问有限公司", new String[]{"feifei_liu@doqi.com.cn"}, new String[]{"刘小飞"}, new String[]{"602920108@qq.com"}, new String[]{"刘飞"}, new String[]{"liuff2513@163.com"}, new String[]{"163飞"}, "哎呦", null, "哎呦喂", "mail.doqi.com.cn", null, null);
		EMailValidation emv=new EMailValidation("feifei_liu@doqi.com.cn", "doqi8888");
//		EMailMode emm=new EMailMode("doqi_system@doqi.com.cn", "北京多企管理顾问有限公司", new String[]{"feifei_liu@doqi.com.cn"}, new String[]{"刘小飞"}, new String[]{"602920108@qq.com"}, new String[]{"刘飞"}, new String[]{"liuff2513@163.com"}, new String[]{"163飞"}, "哎呦", null, "哎呦喂", "mail.doqi.com.cn", null, null);
//		EMailValidation emv=new EMailValidation("doqi_system@doqi.com.cn", "doqi1234");
		sendMail(emm, emv);
	}
}
