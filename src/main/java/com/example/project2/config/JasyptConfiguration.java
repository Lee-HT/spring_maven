package com.example.project2.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfiguration {

    @Bean(name = "jasyptEncryptor")
    public StringEncryptor stringEncryptor() {
        String key = "iaia";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key);    //암호화 시 사용하는 키
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
