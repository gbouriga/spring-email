package com.gbouriga.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyFirstObject extends MyGenericObject {

    private String firstName;

    private String lastName;

    private String address;
}
