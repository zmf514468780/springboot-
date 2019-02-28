package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 10:34
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ShopPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopPayApplication.class,args);
    }
}
