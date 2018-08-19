package com.gbouriga.email.sender.service.impl;

import com.gbouriga.email.dto.EmailContent;
import com.gbouriga.email.sender.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailJavaxSenderImpl implements MailSender {

    @Autowired
    private Session javaxMailSender;

    @Override
    public void sendSimpleMessage(EmailContent emailContent) {
        try {
            MimeMessage message = new MimeMessage(javaxMailSender);

            message.setFrom(new InternetAddress(emailContent.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailContent.getTo()));
            message.setSubject(emailContent.getSubject());
            message.setText(emailContent.getBody());
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageWithAttachments(EmailContent emailContent) {
        //TODO
    }

    @Override
    public void sendHtmlMessage(EmailContent emailContent) {
        try {
            MimeMessage message = new MimeMessage(javaxMailSender);
            message.setFrom(new InternetAddress(emailContent.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailContent.getTo()));
            message.setSubject(emailContent.getSubject());
            message.setText(emailContent.getBody(), "text/html");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void sendHtmlMessageWithAttachments(EmailContent emailContent) {
        //TODO
    }
}
