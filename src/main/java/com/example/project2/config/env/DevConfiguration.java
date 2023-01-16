package com.example.project2.config.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Profile("dev")
@Configuration
public class DevConfiguration implements EnvConfiguration{

    @Value("${IA.loading.message}")
    private String message;

    @Override
    @Bean
    public String getMessage(){
        log.info("[getMessage] devConfiguration 입니다.");
        return message;
    }

}
