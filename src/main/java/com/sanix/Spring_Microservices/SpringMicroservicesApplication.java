package com.sanix.Spring_Microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesApplication.class, args);
	}

}
