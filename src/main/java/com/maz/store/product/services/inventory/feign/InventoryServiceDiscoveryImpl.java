package com.maz.store.product.services.inventory.feign;

import com.maz.store.product.services.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Profile("discovery")
public class InventoryServiceDiscoveryImpl implements InventoryService {

    private final InventoryFeign inventoryFeign;

    @Override
    public Integer getQuantityOnHandById(UUID productId) {
        return inventoryFeign.getQOHById(productId).single().block();
    }

    @Override
    public Integer getQuantityOnHandByUpc(String upc) {
        return inventoryFeign.getQOHByUpc(upc).block();
    }
}
