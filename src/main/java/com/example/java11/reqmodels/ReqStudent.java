package com.example.java11.reqmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ReqStudent {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    private Long groupId;
}
