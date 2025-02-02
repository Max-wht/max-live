package com.max.api;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class  LiveApplication {
    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);简化写法
        SpringApplication springApplication = new SpringApplication(LiveApplication.class);
        springApplication.run(args);
    }
}
