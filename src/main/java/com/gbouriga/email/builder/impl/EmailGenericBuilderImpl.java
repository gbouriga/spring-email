package com.gbouriga.email.builder.impl;

import com.gbouriga.email.builder.EmailBuilder;
import com.gbouriga.email.dto.EmailContent;
import com.gbouriga.email.dto.MyGenericObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailGenericBuilderImpl implements EmailBuilder<MyGenericObject> {

    @Override
    public EmailContent build(MyGenericObject firstObject) {
        return EmailContent.builder()
                .from(firstObject.getFrom())
                .to(firstObject.getTo())
                .subject(firstObject.getSubject())
                .body(firstObject.getText())
                .build();
    }

    @Override
    public List<EmailContent> build(List<MyGenericObject> firstObjects) {
        List<EmailContent> emailContents = new ArrayList<>();
        firstObjects.forEach(firstObject -> emailContents.add(build(firstObject)));
        return emailContents;

    }
}
