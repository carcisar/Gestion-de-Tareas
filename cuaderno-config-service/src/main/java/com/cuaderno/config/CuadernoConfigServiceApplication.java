package com.cuaderno.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;



@EnableConfigServer
@SpringBootApplication
public class CuadernoConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuadernoConfigServiceApplication.class, args);
	}

}
