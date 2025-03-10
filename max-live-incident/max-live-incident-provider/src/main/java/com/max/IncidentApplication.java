package com.max;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:41
 */

@EnableDubbo
@SpringBootApplication
@EnableDiscoveryClient
public class IncidentApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(IncidentApplication.class);
        springApplication.run(args);
    }
}
