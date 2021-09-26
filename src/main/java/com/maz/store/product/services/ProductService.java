package com.maz.store.product.services;

import com.maz.store.model.product.ProductDto;
import com.maz.store.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    ProductDto getProduct(UUID productId, Boolean getQOH);

    List<ProductDto> getAllProducts(Pageable pageable);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(UUID productIt);

}
