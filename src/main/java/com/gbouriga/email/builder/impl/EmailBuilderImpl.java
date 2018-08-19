package com.gbouriga.email.builder.impl;

import com.gbouriga.email.builder.EmailBuilder;
import com.gbouriga.email.dto.EmailContent;

import java.util.ArrayList;
import java.util.List;


public abstract class EmailBuilderImpl<T> implements EmailBuilder<T> {

    @Override
    public abstract EmailContent build(T input);

    @Override
    public List<EmailContent> build(List<T> inputs){
        List<EmailContent> emailContents = new ArrayList<>();
        inputs.forEach(input -> emailContents.add(build(input)));
        return emailContents;
    }
}
