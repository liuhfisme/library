package com.library.utils.mail;

import com.library.utils.poi.DynamicExcelUtil;
import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-07
 */
@Slf4j
public class SendMail {
    public static void main(String[] args) {
        // 配置
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "ismtp.beyondsoft.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.user", "liufeifei02@beyondsoft.com");
        props.setProperty("mail.password", "Liufeifei6174");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("maPil.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.auth", "true");
        Transport transport = null;
        try {
            Session session = Session.getInstance(props);
            transport = session.getTransport();
            long t1 = System.currentTimeMillis();
            transport.connect("liufeifei02@beyondsoft.com", "Liufeifei6174");
            long t2 = System.currentTimeMillis();
            log.info("-----------连接消耗的时长"+(t2-t1)/1000.0);

            // 邮件主题
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject("201903工资明细");
            msg.setFrom(new InternetAddress("liufeifei02@beyondsoft.com"));

            Address[] addresses = new Address[]{new InternetAddress("feifei.work@foxmail.com")};
            msg.setRecipients(Message.RecipientType.TO, addresses);

            // 邮件正文
            MimeMultipart mimeMultipart = new MimeMultipart("mixed");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeMultipart.addBodyPart(mimeBodyPart);
            msg.setContent(mimeMultipart);
            //  -> 构建正文部分
            MimeMultipart bodyMultipart = new MimeMultipart("related");
            mimeBodyPart.setContent(bodyMultipart);
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("刘飞飞（员工编号：000003），你好:<br/><br/>" +
                            "附件是 2019-03-day01 - 2019-03-31 期间内的工资明细，请及时查收！<br/>" +
                            "如有疑问，请联系人力资源部，谢谢。<br/><br/>" +
                            "人力资源部<br/>========================================",
                    "text/html;charset=utf-8");
            bodyMultipart.addBodyPart(htmlPart);

            // 邮件附件
//            File tempFile = new File("C:\\Users\\Administrator\\Desktop\\000003-201903.xls");
            BodyPart attachmentBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(tempFile);
            DataSource source = new ByteArrayDataSource(new ByteArrayInputStream(DynamicExcelUtil.build()), "application/vnd.ms-excel");
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(MimeUtility.encodeText("000003-201903.xls"));
            bodyMultipart.addBodyPart(attachmentBodyPart);

            msg.setSentDate(new Date());
            msg.saveChanges();
            //发送邮件
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            log.error("Send mail error. [{}]", e.getMessage());
        } finally{
            if(transport != null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    log.error("Transport source close error. [{}]", e.getMessage());
                }
            }
        }
    }
}
