/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/4/30 : gong: 新規<br />
 * @version 1.0
 */
@Component
@EnableScheduling
public class SendMsgController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JavaMailSender javaMailSender;

//    @Scheduled(cron = "0 0/2 * * * ?")
    public void setJavaMailSender() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        String text=ContentUtils.getContent();

        message.setFrom("813698205@qq.com");

        message.setTo("1610978367@qq.com");

        message.setSubject(new Date().toString());

        message.setText(text);
        try {
            javaMailSender.send(message);
            logger.info("小黄的测试邮件已发送。");
        } catch (Exception e) {
            logger.error("小黄发送邮件时发生异常了！", e);
        }

    }

    public void setMessageContent(Message message, String ContentType, String RandomCode) throws Exception {
        String Content = "尊敬的用户，您好！您的验证码是： " + RandomCode;

        MimeMultipart mmt = new MimeMultipart();

        MimeBodyPart mbp = new MimeBodyPart();

        // ContentType为编码类型，如GBK等
        if (ContentType == null || ContentType.equals("")) {
            // 由JavaMail来决定编码
            mbp.setText(Content);
        } else {
            // 指定编码格式
            mbp.setContent(Content, ContentType);
        }
        mmt.addBodyPart(mbp);
        message.setContent(mmt);

    }

    public static MimeMessage createImageMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("gacl@sohu.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
        //邮件标题
        message.setSubject("带图片的邮件");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("src\\1.jpg"));
        image.setDataHandler(dh);
        image.setContentID("xxx.jpg");
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");
        message.setContent(mm);
        message.saveChanges();
        //将创建好的邮件写入到E盘以文件的形式进行保存
        message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
        //返回创建好的邮件
        return message;
    }

    /**
     * @param session
     * @return
     * @throws Exception
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @Anthor:孤傲苍狼
     */
    public static MimeMessage createSimpleMail(Session session)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom("Your friend");
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("813698205@qq.com"));//1689174495@qq.com
        //邮件的标题
        message.setSubject("lili");
        //邮件的文本内容
        message.setContent("你好啊！", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }

}
