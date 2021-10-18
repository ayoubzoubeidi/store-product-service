package com.maz.store.product.web.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface ProductHandler {

    Mono<ServerResponse> getProduct(ServerRequest request);

    Mono<ServerResponse> getProductPage(ServerRequest serverRequest);

    Mono<ServerResponse> saveProduct(ServerRequest request);

    Mono<ServerResponse> updateProduct(ServerRequest request);

    Mono<ServerResponse> validateInventory(ServerRequest serverRequest);

    Mono<ServerResponse> deleteProduct(ServerRequest request);
}
