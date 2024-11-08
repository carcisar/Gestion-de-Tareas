package com.cuaderno.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class CuadernoAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuadernoAuthServiceApplication.class, args);
	}

}
