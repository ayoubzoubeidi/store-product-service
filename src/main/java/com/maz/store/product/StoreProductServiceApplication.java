package com.maz.store.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoreProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreProductServiceApplication.class, args);
    }

}
