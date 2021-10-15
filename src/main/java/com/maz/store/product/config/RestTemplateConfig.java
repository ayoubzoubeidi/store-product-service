package com.maz.store.product.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {

    public static final String INVENTORY_URL = "/api/v1/inventory/";

    @Bean
    public RestTemplateBuilder restTemplate() {
        // Do any additional configuration here
        return new RestTemplateBuilder();
    }
}
