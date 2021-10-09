package com.maz.store.product.services.inventory.feign;

import feign.Param;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.UUID;



@ReactiveFeignClient(name = "inventory-service")
public interface InventoryFeign {

    @RequestMapping(method = RequestMethod.GET, value = "${store.rest.product-QOH-url-by-upc}")
    Mono<Integer> getQOHByUpc(@PathVariable("upc") String upc);

    @RequestMapping(method = RequestMethod.GET, value = "${store.rest.product-QOH-url-by-id}")
    Mono<Integer> getQOHById(@Param("productId") UUID productId);

}
