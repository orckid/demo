package com.orc.demo.controller;

import com.orc.demo.common.MailBean;
import com.orc.demo.util.mail.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author orcki
 */
@Controller
public class MailController {
    private static final Logger LOG = LoggerFactory.getLogger(MailController.class);

    private MailUtil mailUtil;

    @Value("${spring.mail.send}")
    private String mailTo;

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @RequestMapping("/mail")
    @ResponseBody
    public String mail() {
        LOG.info("开始发送邮件...");
        String[] sendTo = mailTo.split(",");
        MailBean mailBean = new MailBean(sendTo, "测试邮件", "SpringBoot 测试邮件");

        try {
            mailUtil.sendMail(mailBean);
            return "发送成功！收件人：" + mailTo;
        } catch (Exception e) {
            LOG.error("发送邮件失败，请重试...", e);
        }
        return "发送失败！";
    }
}
