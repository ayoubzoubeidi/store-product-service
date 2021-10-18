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

    public static String BASE_URL = "/api/v1/products";

    private final ProductHandler productHandler;


    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()

                .GET(BASE_URL, productHandler::getProductPage)

                .GET(BASE_URL + "/{productId}", productHandler::getProduct)

                .GET(BASE_URL + "/{upc}/validate", productHandler::validateInventory)

                .POST(BASE_URL, accept(MediaType.APPLICATION_JSON), productHandler::saveProduct)

                .PUT(BASE_URL, accept(MediaType.APPLICATION_JSON), productHandler::updateProduct)

                .DELETE(BASE_URL + "/{productId}", productHandler::deleteProduct)

                .build();
    }

}
