package com.gbouriga.email.builder;

import com.gbouriga.email.dto.EmailContent;

import java.util.List;

public interface EmailBuilder<T> {

    EmailContent build(T input);

    List<EmailContent> build(List<T> inputs);
}
