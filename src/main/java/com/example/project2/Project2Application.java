package com.example.project2;

import com.example.project2.config.ProfileManager;
import com.example.project2.config.env.EnvConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Project2Application {

	@Autowired
	public Project2Application(EnvConfiguration envConfiguration, ProfileManager profileManager){
		log.info(envConfiguration.getMessage());
		profileManager.printActiveProfiles();
	}
	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}

}
