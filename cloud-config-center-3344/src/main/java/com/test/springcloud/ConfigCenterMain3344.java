package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344
{
    // use bus to publish broadcast to it client, so the when remote git is update, the config center will broadcast,
    // and client then listen to config center will refresh it
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
