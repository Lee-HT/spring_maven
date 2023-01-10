package com.example.project2.service;

import com.example.project2.data.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {
    public String getIA();

    public String getName();

    public String getName2();

    public ResponseEntity<MemberDTO> postDTO();

    public ResponseEntity<MemberDTO> addHeader();
}
