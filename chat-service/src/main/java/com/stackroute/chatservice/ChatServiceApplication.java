package com.stackroute.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.stackroute.chatservice")
@EnableSwagger2
@EnableEurekaClient
public class ChatServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChatServiceApplication.class, args);
	}

}
