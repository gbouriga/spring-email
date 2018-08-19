package com.gbouriga.email.caller;

import com.gbouriga.email.builder.EmailBuilder;
import com.gbouriga.email.dto.EmailContent;
import com.gbouriga.email.dto.MyFirstObject;
import com.gbouriga.email.dto.MyGenericObject;
import com.gbouriga.email.sender.service.impl.MailSpringSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailCaller {

    @Autowired
    private MailSpringSenderImpl mailSpringSender;

    @Autowired
    private EmailBuilder<MyGenericObject> emailGenericObjectBuilder;


    @Autowired
    private EmailBuilder<MyFirstObject> emailFirstObjectBuilder;


    public void callSimpleGenereicEmail(List<MyGenericObject> myGenericObjects){
        List<EmailContent> emailContents = emailGenericObjectBuilder.build(myGenericObjects);
        emailContents.forEach(emailContent ->  mailSpringSender.sendSimpleMessage(emailContent));
    }

    public void callHtmlMyFirstObjectEmail(MyFirstObject myFirstObject){
        EmailContent emailContent = emailFirstObjectBuilder.build(myFirstObject);
        mailSpringSender.sendHtmlMessage(emailContent);
    }
}
