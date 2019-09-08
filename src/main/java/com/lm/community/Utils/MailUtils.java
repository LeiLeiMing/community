package com.lm.community.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    //实现向QQ邮件发送验证码
    //to:收件人邮箱  subject 主题   content 邮件内容
    public static void sendMsg(String to ,String subject ,String content) throws Exception{
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");//认证
        props.put("mail.smtp.host", "smtp.qq.com");  //设置主机地址   smtp.qq.com    smtp.sina.com
        props.put("mail.smtp.port","587");  //QQ邮件的开放端口

        //发件人方
        //qq邮箱的账号
        props.put("mail.user","1396738835@qq.com");
        //密码（就是16位STMP口令）
        props.put("mail.password","wnrrjpyokmewjfjd");
        //构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名  密码
                String username = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(username,password);
            }
        };
        //2.产生一个用于邮件发送的Session对象
        Session session = Session.getInstance(props,authenticator);

        //3.产生一个邮件的消息对象
        MimeMessage message = new MimeMessage(session);

        //4.设置消息的发送者
        InternetAddress fromAddr = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(fromAddr);
        //5.设置消息的接收者
        Address toAddr = new InternetAddress(to);
        //TO 直接发送  CC抄送    BCC密送
        message.setRecipient(MimeMessage.RecipientType.TO, toAddr);
        //6.设置主题
        message.setSubject(subject);
        //内容
        message.setContent(content,"text/html;charset=UTF-8");
        //发送
        Transport.send(message);
        System.out.println("发送成功");
    }
}
