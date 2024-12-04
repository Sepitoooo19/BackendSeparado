package com.example.FollowRequests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FollowRequestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FollowRequestsApplication.class, args);
	}

}
