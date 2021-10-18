package com.maz.store.product.services;

import com.maz.store.model.product.ProductDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Mono<UUID> saveProduct(Mono<ProductDto> productDto);

    Mono<ProductDto> getProduct(UUID productId, Boolean getQOH);

    Flux<ProductDto> getAllProducts(Pageable pageable);

    Mono<Void> updateProduct(Mono<ProductDto> productDto);

    Mono<UUID> validateInventory(String upc);

    Mono<Void> deleteProduct(UUID productIt);

}
