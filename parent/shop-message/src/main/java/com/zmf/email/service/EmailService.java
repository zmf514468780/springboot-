package com.zmf.email.service;

import com.alibaba.fastjson.JSONObject;

import com.zmf.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 14:13
 * @Description:
 *  邮件发送
 *  springmail
 *  通过JavaMailSender 发送 SimpleMailMessage
 */
@Service
@Slf4j
public class EmailService implements MessageAdapter {
    @Value("${msg.subject}")
    private String subject;
    @Value("${msg.text}")
    private String text;
    @Value("${spring.mail.username}")
    private String from ;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendMsg(JSONObject body) {
        String email = body.getString("email");
        if(StringUtils.isEmpty(email)){
            return ;
        }
        log.info("消息服务平台发送消息：{}"+email);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 来自账号 自己发自己
        simpleMailMessage.setFrom(from);
        // 发送账号
        simpleMailMessage.setTo(from); // 先自己发送给自己吧
        // 标题
        simpleMailMessage.setSubject(subject);
        // 内容
        simpleMailMessage.setText(text.replace("{}", email));
        // 发送邮件
        javaMailSender.send(simpleMailMessage);
        log.info("消息服务平台发送邮件:{} 完成", email);
    }
}
