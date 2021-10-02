package com.maz.store.product.services.inventory.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

import static com.maz.store.product.config.RestTemplateConfig.INVENTORY_URL;

@FeignClient("inventory-service")
public interface InventoryFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = INVENTORY_URL + "/{upc}/upc")
    ResponseEntity<Integer> getQOHByUpc(@PathVariable(value = "upc") String upc);

    @RequestMapping(method = RequestMethod.GET, value = INVENTORY_URL + "/{productId}/id")
    ResponseEntity<Integer> getQOHById(@PathVariable(value = "productId") UUID productId);

}
