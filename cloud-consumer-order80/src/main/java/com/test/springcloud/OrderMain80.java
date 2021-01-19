package com.test.springcloud;

import com.test.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class) //import own load balance rule



//@EnableDiscoveryClient //如果第一次用@EnableEurekaClient加不了，用这个，成功了可以注释，不知道为什么一定要加这个才可以注册进去eureka server,
public class OrderMain80 {

    public static void main(String[] args)
    {
        SpringApplication.run(OrderMain80.class, args);
    }

}
