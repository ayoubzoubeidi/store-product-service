package com.maz.store.product.services.inventory.feign;

import com.maz.store.product.services.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactivefeign.cloud2.CloudReactiveFeign;
import reactivefeign.webclient.WebReactiveFeign;

import java.util.UUID;

import static com.maz.store.product.config.RestTemplateConfig.INVENTORY_URL;

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
