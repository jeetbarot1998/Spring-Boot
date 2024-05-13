package com.demo.springboot.SpringBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableJpaRepositories(basePackages =
		{"com.demo.springboot.SpringBootApp.topic",
		"com.demo.springboot.SpringBootApp.courses",
		"com.demo.springboot.SpringBootApp.securityExample",
		"com.demo.springboot.SpringBootApp.JWT"})
@EnableOAuth2Sso
public class SpringBootAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootAppApplication.class, args);
	}

}
