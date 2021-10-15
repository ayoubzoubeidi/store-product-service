package com.maz.store.product.web.routers;

import com.maz.store.product.web.handlers.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@RequiredArgsConstructor

@Configuration
public class ProductRouter {

    private final ProductHandler productHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler handler) {
        return RouterFunctions.route()
                .path("/api/v1/products", builder -> builder

                        .GET(accept(MediaType.APPLICATION_JSON), handler::getProductPage))

                .GET("/{productId}",
                        accept(MediaType.APPLICATION_JSON), handler::getProduct)

                .POST(accept(MediaType.APPLICATION_JSON), handler::saveProduct)

                .PUT(accept(MediaType.APPLICATION_JSON), handler::updateProduct)
                .build();
    }

}
