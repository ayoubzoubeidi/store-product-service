package com.maz.store.product.repositories;

import com.maz.store.product.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends ReactiveMongoRepository<Product, UUID> {

    Mono<Boolean> existsByUpc(String upc);
    Mono<Product> findByUpc(String upc);

}
