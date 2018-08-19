package com.gbouriga.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyGenericObject {

    protected String from;

    protected String to;

    protected String subject;

    protected String text;
}
