package com.rcf.servicesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesServiceApplication.class, args);
    }

}
