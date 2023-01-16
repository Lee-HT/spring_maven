package com.example.project2.config.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
* PackageName : com.example.project2
* FileName : LocalConfiguration
* Author : IA
* Date : 2023-01-16
* Description :
 */

@Slf4j
@Profile("local")
@Configuration
public class LocalConfiguration implements EnvConfiguration{

    @Value("##local 환경으로 실행되었습니다")
    private String message;

    @Override
    @Bean
    public String getMessage(){
        log.info("[getMessage] localConfiguration 입니다");
        return message;
    }

}
