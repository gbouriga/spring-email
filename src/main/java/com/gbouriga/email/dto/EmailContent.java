package com.gbouriga.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class EmailContent {

    private String from;

    private String to;

    private String subject;

    private String text;

    private List<String> attachments;

}
