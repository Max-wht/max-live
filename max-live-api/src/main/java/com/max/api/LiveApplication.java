package com.max.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class  LiveApplication {
    public static void main(String[] args) {

        //SpringApplication.run(Application.class, args);简化写法
        SpringApplication springApplication = new SpringApplication(LiveApplication.class);
        springApplication.run(args);
    }

}
