package com.example.project2.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    @Test
    void encryptTest(){
        String id = "root";
        String password = "z8jw29q";

        System.out.println(jasyptEncoding(id));
        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value){
        String key = "iaia";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

}
