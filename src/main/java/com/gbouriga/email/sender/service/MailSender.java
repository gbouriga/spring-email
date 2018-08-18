package com.gbouriga.email.sender.service;

import com.gbouriga.email.dto.EmailContent;

public interface MailSender {

    void sendSimpleMessage(EmailContent emailContent);

    void sendSimpleMessageWithAttachments(EmailContent emailContent);

    void sendHtmlMessage(EmailContent emailContent);

    void sendHtmlMessageWithAttachments(EmailContent emailContent);



}
