package com.example.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter @Setter
public class MemberDTO {
    private String name;
    private String email;
    private String organization;

}
