package com.orc.demo.common;

/**
 * @author orcki
 */
public class MailBean {

    /**
     * 收件人
      */
    private String mailTo;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    public MailBean() {
    }

    public MailBean(String mailTo, String subject, String content) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
