package com.test.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig
{

     @Bean
     @LoadBalanced // if got from cluster eureka service, we need to add this @LoadBalanced, else will error don't know get from which server port
     public RestTemplate getRestRTemplate()
     {
         return new RestTemplate();
     }
}
