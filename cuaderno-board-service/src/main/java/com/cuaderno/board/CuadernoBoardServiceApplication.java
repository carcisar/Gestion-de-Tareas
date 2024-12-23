package com.cuaderno.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CuadernoBoardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuadernoBoardServiceApplication.class, args);
	}

}
