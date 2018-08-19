package com.gbouriga.email.builder.impl;

import com.gbouriga.email.dto.EmailContent;
import com.gbouriga.email.dto.MyFirstObject;
import org.springframework.stereotype.Service;

@Service
public class EmailMyFirstObjectBuilderImpl extends EmailBuilderImpl<MyFirstObject> {

    @Override
    public EmailContent build(MyFirstObject myFirstObject) {
        String body = "<p1>" +
                "first name : " + myFirstObject.getFirstName() +
                "\n" +
                "last name : " + myFirstObject.getLastName() +
                "\n" +
                "address : " + myFirstObject.getAddress() +
                "\n" +
                "</p1>";
        return EmailContent
                .builder()
                .from(myFirstObject.getFrom())
                .to(myFirstObject.getTo())
                .body(body)
                .build();
    }

}
