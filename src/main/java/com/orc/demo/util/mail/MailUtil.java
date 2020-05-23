package com.orc.demo.util.mail;

import com.orc.demo.common.MailBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author orcki
 */
@Component
public class MailUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MailUtil.class);

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param mailBean 邮件信息
     */
    public void sendMail(MailBean mailBean) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(mailBean.getMailTo());
        mailMessage.setSubject(mailBean.getSubject());
        mailMessage.setText(mailBean.getContent());

        javaMailSender.send(mailMessage);
        LOG.info("邮件发送成功，发送时间：{}", LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
    }
}
