package com.gbouriga.email.sender.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import java.util.Properties;

@Configuration
public class MailSenderConfiguration {

    @Value("${email.server.host:smtp.gmail.com}")
    private String host;

    @Value("${email.server.port:587}")
    private int port;

    @Value("${email.server.username:}")
    private String username;

    @Value("${email.server.password:}")
    private String password;


    //if need backup server when the first one is down
    @Value("${email.server.host.backup:}")
    private String hostBackup;

    @Value("${email.server.port.backup:587}")
    private int portBackup;

    @Value("${email.server.username.backup:}")
    private String usernameBackup;

    @Value("${email.server.password.backup:}")
    private String passwordBackup;

    @Value("${email.server.host.backup.active:false}")
    private boolean isbackupActive;


    @Bean
    public JavaMailSenderImpl getSpringMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        if (!isbackupActive) {
            mailSender.setHost(host);
            mailSender.setPort(port);

            mailSender.setUsername(username);
            mailSender.setPassword(password);
        } else {
            mailSender.setHost(hostBackup);
            mailSender.setPort(portBackup);

            mailSender.setUsername(usernameBackup);
            mailSender.setPassword(passwordBackup);
        }


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


    @Bean
    public Session getJavaxMailSender() {
        //TODO complete if need more properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        return Session.getDefaultInstance(properties);

    }
}
