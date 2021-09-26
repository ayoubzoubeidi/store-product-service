package com.maz.store.product.web.mappers.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface ProductHandler {

    public Mono<ServerResponse> getProduct(ServerRequest request);

    public Mono<ServerResponse> getProductPage(ServerRequest serverRequest);

    public Mono<ServerResponse> saveProduct(ServerRequest request);

    public Mono<ServerResponse> updateProduct(ServerRequest request);
}
