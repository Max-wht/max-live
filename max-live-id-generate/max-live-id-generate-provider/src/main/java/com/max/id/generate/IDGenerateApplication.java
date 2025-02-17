package com.max.id.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Max
 * @description    
 * @date 2025/2/7 15:59
 */

@SpringBootApplication
public class IDGenerateApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(IDGenerateApplication.class);
        springApplication.run(args);
    }

}
