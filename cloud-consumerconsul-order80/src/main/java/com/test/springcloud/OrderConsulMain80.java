package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderConsulMain80 {

    public static void main(String[] args)
    {
        SpringApplication.run(OrderConsulMain80.class, args);
    }
}
