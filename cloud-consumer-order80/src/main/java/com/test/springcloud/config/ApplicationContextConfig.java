package com.test.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig
{

    @Bean
     public RestTemplate getRestRTemplate()
     {
         return new RestTemplate();
     }
}
