package com.maz.store.product.config;

import com.maz.store.product.services.inventory.feign.InventoryFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.ReactiveFeign;
import reactivefeign.cloud2.CloudReactiveFeign;

@Configuration
public class OpenFeignClient {

}
