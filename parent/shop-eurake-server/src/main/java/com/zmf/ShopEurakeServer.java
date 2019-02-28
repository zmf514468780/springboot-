package com.zmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 23:31
 * @Description: springboot eureka 注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class ShopEurakeServer {
    public static void main(String[] args) {
        SpringApplication.run(ShopEurakeServer.class,args);
    }
}
