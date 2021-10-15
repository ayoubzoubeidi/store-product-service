package com.maz.store.product.services.inventory.resttemplate;

import com.maz.store.product.services.inventory.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Service
@Profile("!discovery")
@ConfigurationProperties(prefix = "store.rest", ignoreInvalidFields = true)
@Slf4j
public class InventoryServiceRestTemplateImpl implements InventoryService {

    private final RestTemplate restTemplate;
    private String inventoryBaseUrl;
    private String productQOHUrlById;
    private String productQOHUrlByUpc;


    public InventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setInventoryBaseUrl(String inventoryBaseUrl) {
        this.inventoryBaseUrl = inventoryBaseUrl;
    }

    public void setProductQOHUrlById(String productQOHUrlById) {
        this.productQOHUrlById = productQOHUrlById;
    }

    public void setProductQOHUrlByUpc(String productQOHUrlByUpc) {
        this.productQOHUrlByUpc = productQOHUrlByUpc;
    }

    @Override
    public Integer getQuantityOnHandById(UUID productId) {
        return Objects.requireNonNull(restTemplate.exchange(inventoryBaseUrl + productQOHUrlById, HttpMethod.GET, null,
                Integer.class, productId).getBody());    }

    @Override
    public Integer getQuantityOnHandByUpc(String upc) {

      Integer result = Objects.requireNonNull(restTemplate.exchange(inventoryBaseUrl + productQOHUrlByUpc, HttpMethod.GET, null,
              Integer.class, upc).getBody());

        System.err.println("QOH for " + upc + " = " + result);

        return result;

    }
}
