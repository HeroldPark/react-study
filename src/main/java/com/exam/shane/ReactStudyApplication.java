package com.exam.shane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ReactStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactStudyApplication.class, args);
	}

}
