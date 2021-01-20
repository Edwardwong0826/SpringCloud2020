package com.test.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig
{
    @Bean
    Logger.Level feignLoggerLevel()
    {
        // feign got 4 type of log level : NONE, BASIC, HEADERS, FULL
        return Logger.Level.FULL;
    }
}
