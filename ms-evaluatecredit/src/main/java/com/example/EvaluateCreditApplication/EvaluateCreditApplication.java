package com.example.EvaluateCreditApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EvaluateCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluateCreditApplication.class, args);
	}

}
