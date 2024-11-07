package com.cuaderno.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CuadernoDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuadernoDiscoveryServiceApplication.class, args);
	}

}
