package com.example.project2.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class MemberDTO {
    private String name;
    private String email;
    private String organization;

}
