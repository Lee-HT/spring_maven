package com.example.project2.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JasyptConfiguration {

    // -Djasypt.encryptor.password=iaia
    @Value("${jasypt.encryptor.password}")
    private String password;

    @Bean(name = "jasyptEncryptor")
    public StringEncryptor stringEncryptor() {
//        String password = "iaia";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);    //암호화 시 사용하는 키
        config.setAlgorithm("PBEWithMd5andDES");  // 암호화 알고리즘
        config.setKeyObtentionIterations("1000");   // 반복 해시 횟수
        config.setPoolSize("1");   // 인스턴스 pool
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");   // salt 생성 클래스
//        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");   //인코딩 방식
        encryptor.setConfig(config);
        return encryptor;
    }

}
