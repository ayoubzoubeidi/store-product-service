package com.maz.store.product.web.mappers;

import com.maz.store.model.product.ProductDto;
import com.maz.store.product.domain.Product;
import com.maz.store.product.services.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProductMapperDecorator implements ProductMapper{

    private ProductMapper productMapper;
    private InventoryService inventoryService;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        ProductDto dto = productMapper.productToProductDto(product);
        dto.setQuantityOnHand(inventoryService.getQuantityOnHandByUpc(product.getUpc()));
        return dto;
    }

}
