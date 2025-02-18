package com.max.id.generate;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Max
 * @description    
 * @date 2025/2/7 15:59
 */

@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient

public class IDGenerateApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(IDGenerateApplication.class);
        springApplication.run(args);
    }

}
