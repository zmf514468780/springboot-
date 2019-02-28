package com.zmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Auther: zmf
 * @Date: 2019-01-17 09:52
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PcWebApp {
    public static void main(String[] args) {
        SpringApplication.run(PcWebApp.class,args);
    }
}
