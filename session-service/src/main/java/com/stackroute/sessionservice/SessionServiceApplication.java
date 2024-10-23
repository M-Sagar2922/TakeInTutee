package com.stackroute.sessionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//<<<<<<< HEAD

//=======
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//>>>>>>> 1e289c3fe4b99d9bcd7fe8c3c83d762a98f2e07a
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//<<<<<<< HEAD

//=======
@EnableEurekaClient
//>>>>>>> 1e289c3fe4b99d9bcd7fe8c3c83d762a98f2e07a
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
public class SessionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionServiceApplication.class, args);
    }

}
