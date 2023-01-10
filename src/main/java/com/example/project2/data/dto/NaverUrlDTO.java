package com.example.project2.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NaverUrlDTO {
    private String message;
    private String code;
    private Result result;

    @Getter @Setter
    public static class Result {
        private String hash;
        private String url;
        private String orgUrl;
    }

}
