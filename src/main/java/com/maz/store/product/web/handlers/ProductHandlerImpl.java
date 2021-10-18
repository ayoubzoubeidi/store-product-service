package com.maz.store.product.web.handlers;

import com.maz.store.model.product.ProductDto;
import com.maz.store.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@RequiredArgsConstructor

@Component
public class ProductHandlerImpl implements ProductHandler {

    private final ProductService productService;

    @Override
    public Mono<ServerResponse> getProduct(ServerRequest request) {

        UUID productId = UUID.fromString(request.pathVariable("productId"));
        Boolean getQOH = Boolean.valueOf(request.queryParams().getFirst("getQOH"));
        return ServerResponse.ok().body(productService.getProduct(productId, getQOH), ProductDto.class);
    }

    @Override
    public Mono<ServerResponse> getProductPage(ServerRequest request) {
        Integer page;
        Integer size;

        if (request.queryParams().getFirst("page") != null) {
            page = Integer.parseInt(Objects.requireNonNull(request.queryParams().getFirst("page")));
        } else {
            page = 1;
        }

        if (request.queryParams().getFirst("size") != null) {
            size = Integer.parseInt(Objects.requireNonNull(request.queryParams().getFirst("size")));
        } else {
            size = 20;
        }

        Pageable pageable =
                PageRequest.of(page, size);
        return ServerResponse.ok().body(productService.getAllProducts(pageable), ProductDto.class);
    }

    @Override
    public Mono<ServerResponse> saveProduct(ServerRequest request) {

        Mono<UUID> id = productService.saveProduct(request.bodyToMono(ProductDto.class));

        return ServerResponse.created(URI.create(id.toString())).body(id, UUID.class);
    }

    @Override
    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        return productService.updateProduct(request.bodyToMono(ProductDto.class))
                .flatMap((val) -> ServerResponse.noContent().build());
    }

    @Override
    public Mono<ServerResponse> validateInventory(ServerRequest request) {
        String upc = request.pathVariable("upc");
        return ServerResponse.ok().body(productService.validateInventory(upc), UUID.class);
    }

    @Override
    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        UUID productId = UUID.fromString(request.pathVariable("productId"));
        return productService.deleteProduct(productId)
                .flatMap(val -> ServerResponse.noContent().build());
    }

}
