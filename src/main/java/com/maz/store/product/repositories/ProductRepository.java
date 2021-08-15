package com.maz.store.product.repositories;

import com.maz.store.product.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ProductRepository extends ReactiveMongoRepository<Product, UUID> {
}
