package com.competitions.prijava_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PrijavaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrijavaServiceApplication.class, args);
    }

}
