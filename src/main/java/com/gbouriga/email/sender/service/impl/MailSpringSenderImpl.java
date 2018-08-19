package com.gbouriga.email.sender.service.impl;

import com.gbouriga.email.dto.EmailContent;
import com.gbouriga.email.sender.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSpringSenderImpl implements MailSender {

    @Autowired
    private JavaMailSender springMailSender;


    public void sendSimpleMessage(EmailContent emailContent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailContent.getFrom());
        message.setTo(emailContent.getTo());
        message.setSubject(emailContent.getSubject());
        message.setText(emailContent.getBody());
        springMailSender.send(message);
    }

    @Override
    public void sendSimpleMessageWithAttachments(EmailContent emailContent) {
        MimeMessage message = springMailSender.createMimeMessage();
        MimeMessageHelper helper = mimeMessageHelper(emailContent, message, false);
        emailAddAttachments(emailContent, helper);
        springMailSender.send(message);
    }

    @Override
    public void sendHtmlMessage(EmailContent emailContent) {
        MimeMessage message = springMailSender.createMimeMessage();
        mimeMessageHelper(emailContent, message, true);
        springMailSender.send(message);

    }

    @Override
    public void sendHtmlMessageWithAttachments(EmailContent emailContent) {
        MimeMessage message = springMailSender.createMimeMessage();
        MimeMessageHelper helper = mimeMessageHelper(emailContent, message, true);
        emailAddAttachments(emailContent, helper);
        springMailSender.send(message);

    }

    private MimeMessageHelper mimeMessageHelper(EmailContent emailContent, MimeMessage message, boolean isHtmlHelper) {
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailContent.getFrom());
            helper.setTo(emailContent.getTo());
            helper.setSubject(emailContent.getSubject());
            if (isHtmlHelper) {
                helper.setText((emailContent.getBody()), true);
            } else {
                helper.setText(emailContent.getBody());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return helper;

    }

    private void emailAddAttachments(EmailContent emailContent, MimeMessageHelper helper) {
        emailContent.getAttachments().forEach(attachment -> {
            FileSystemResource file = new FileSystemResource(attachment);
            try {
                helper.addAttachment(file.getFilename(), file);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }


}
