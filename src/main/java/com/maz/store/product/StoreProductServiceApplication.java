package com.maz.store.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
@EnableEurekaClient
public class StoreProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreProductServiceApplication.class, args);
    }

}
