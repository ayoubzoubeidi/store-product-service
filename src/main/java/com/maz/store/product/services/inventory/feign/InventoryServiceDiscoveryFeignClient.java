package com.maz.store.product.services.inventory.feign;

import com.maz.store.product.services.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@Profile("discovery")
@RequiredArgsConstructor
public class InventoryServiceDiscoveryFeignClient implements InventoryService {

    private final InventoryFeignClient inventoryFeignClient;

    @Override
    public Integer getQuantityOnHandById(UUID productId) {
        return Objects.requireNonNull(inventoryFeignClient.getQOHById(productId).getBody());
    }

    @Override
    public Integer getQuantityOnHandByUpc(String upc) {
        return inventoryFeignClient.getQOHByUpc(upc).getBody();
    }
}
