package com.maz.store.product.services;

import com.maz.store.model.ProductDto;

import java.util.UUID;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    ProductDto getProduct(UUID productId);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(UUID productIt);

}
