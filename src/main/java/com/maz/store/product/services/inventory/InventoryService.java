package com.maz.store.product.services.inventory;

import java.util.UUID;

public interface InventoryService {

    Integer getQuantityOnHandById(UUID productId);

    Integer getQuantityOnHandByUpc(String upc);

}
