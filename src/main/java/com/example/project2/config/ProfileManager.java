package com.example.project2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProfileManager {
    private final Environment environment;

    @Autowired
    public ProfileManager(Environment environment){
        this.environment = environment;
    }

    public void printActiveProfiles(){
        log.info("[printActiveProfiles] active Profiles size : {}",environment.getActiveProfiles().length);
        for(String profile : environment.getActiveProfiles()){
            log.info("[printActiveProfiles] profiles: : {}",profile);
        }
    }

}
