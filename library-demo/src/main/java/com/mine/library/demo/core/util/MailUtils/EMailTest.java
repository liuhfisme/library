package com.mine.library.demo.core.util.MailUtils;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EMailTest{
	public static void ayalyseUnRead(){
//		Folder folder=EMailTool.getFolder("smtp.163.com", "imap", "imap.163.com", "liuff2513@163.com", "liuhaifei6174");
		/*获取该邮箱下的Folder收件箱*/
		Folder folder=EMailManager.getFolder_IMAP_BY_NAME("imap.163.com", "liuff2513@163.com", "liuhaifei6174", "INBOX");
		Message[] messages;
		try {
			messages = folder.getMessages();
			for(int i=0;i<messages.length;i++){
				MimeMessage message=(MimeMessage) messages[i];
				Flags flags=message.getFlags();
				Flag[] flag=flags.getSystemFlags();
				boolean seen=false;
				for(int j=0;j<flag.length;j++){
					if(flag[j]==Flag.SEEN){
						seen=true;
						break;
					}
				}
				if(seen==true){
					System.out.println(i);
				}else{
					System.out.println(i+"-----"+message.getSubject()+"-----"+seen+message.getReceivedDate().toLocaleString());
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public void test(){
		boolean ss=false;
		String s=ss+"";
		System.out.println(s);
	}
}

