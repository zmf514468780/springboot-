package com.zmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 14:16
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class MessageServer{
    public static void main(String[] args) {
        SpringApplication.run(MessageServer.class,args);
    }
}
