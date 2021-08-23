package com.maz.store.product.services;

import com.maz.store.model.product.ValidationRequest;
import com.maz.store.product.config.JmsConfig;
import com.maz.store.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationListener {

    private final ProductRepository productRepository;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void validationListener(ValidationRequest request) {
    }

}
