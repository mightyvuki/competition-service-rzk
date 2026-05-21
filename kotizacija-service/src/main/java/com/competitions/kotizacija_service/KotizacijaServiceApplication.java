package com.competitions.kotizacija_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KotizacijaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotizacijaServiceApplication.class, args);
    }

}
