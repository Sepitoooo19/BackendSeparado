package com.example.TotalCost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TotalCostApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotalCostApplication.class, args);
	}

}
