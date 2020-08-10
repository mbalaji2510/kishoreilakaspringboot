package com.letsstartcoding.springbootrestapioauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer 
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("Welcome to Spring Boot OAuth2 Application!!!");
	}
}
