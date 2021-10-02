package com.maz.store.product.inventory;

import com.maz.store.model.product.ProductDto;
import com.maz.store.product.domain.Product;
import com.maz.store.product.repositories.ProductRepository;
import com.maz.store.product.web.mappers.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.jgroups.util.Util.assertEquals;

@SpringBootTest
class InventoryApiClientTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Test
    public void testGetQOH() {
        // mock data on inventory server, upc = 1234 QOH = 50
        Product createdProduct = createProduct();

        System.err.println(createdProduct);

        ProductDto dto = productMapper.productToProductDto(createdProduct);

        System.err.println(dto.getQuantityOnHand());
        assertEquals(50, dto.getQuantityOnHand());

    }

    private Product createProduct() {
        return productRepository.save(
                Product.builder().id(UUID.randomUUID()).upc("1234").label("test").cost(new BigDecimal("56.23")).build()
        ).block();
    }

}
