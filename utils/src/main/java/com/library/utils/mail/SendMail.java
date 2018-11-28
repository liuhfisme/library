package com.library.utils.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * @ClassName: SendMail
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/9/3 11:31
 */
public class SendMail {
    public static void main(String[] args) {
        Transport transport = null;
        StringBuffer successMail = new StringBuffer();
        try {
            Properties props = new Properties();//配置
            props.setProperty("mail.smtp.host", "172.26.159.253");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.user", "wujingyi@tangche.com");
            props.setProperty("mail.password", "Wjy001");
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("maPil.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.auth", "true");
            Set keys = props.keySet();
            for(Object key:keys) {
                System.out.println(key+"----"+props.get(key));
            }
            Session session = Session.getInstance(props);
            transport = session.getTransport();
            long t1 = System.currentTimeMillis();
            transport.connect("wujingyi@tangche.com", "Wjy001");
            long t2 = System.currentTimeMillis();
            System.out.println("-----------连接消耗的时长"+(t2-t1)/1000.0);

            MimeMessage msg = new MimeMessage(session);
            msg.setSubject("来自刘飞飞的邀请");
            msg.setFrom(new InternetAddress("wujingyi@tangche.com"));

            Address addresses[] = new Address[]{new InternetAddress("feifei.work@foxmail.com")};
            msg.setRecipients(Message.RecipientType.TO, addresses);

            MimeMultipart mimeMultipart = new MimeMultipart("mixed");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeMultipart.addBodyPart(mimeBodyPart);
            msg.setContent(mimeMultipart);
            //构建正文部分
            MimeMultipart bodyMultipart = new MimeMultipart("related");
            mimeBodyPart.setContent(bodyMultipart);
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("你好，我是大帅哥！","text/html;charset=utf-8");
            bodyMultipart.addBodyPart(htmlPart);

            msg.setSentDate(new Date());
            msg.saveChanges();
            //发送邮件
            transport.sendMessage(msg, msg.getAllRecipients());


            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
        } finally{
            if(transport != null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
