package com.maz.store.product.services;

import com.maz.store.model.order.OrderLineDto;
import com.maz.store.model.product.ValidationRequest;
import com.maz.store.model.product.ValidationResponse;
import com.maz.store.product.config.JmsConfig;
import com.maz.store.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationListener {

    private final JmsTemplate jmsTemplate;
    private final ProductRepository productRepository;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void validationListener(ValidationRequest request) {
        AtomicBoolean isValid = new AtomicBoolean(true);
        request.getOrder().getOrderLines().forEach(
                (orderLineDto) -> {
                    if (!productRepository.existsByUpc(orderLineDto.getUpc()).block()) {
                        isValid.set(false);
                    }
                }
        );

        log.info("Sending validation response ");
        jmsTemplate.convertAndSend(JmsConfig.VALIDATION_RESPONSE_QUEUE,
                ValidationResponse.builder().orderId(request.getOrder().getId()).isValid(isValid.get()).build());

    }

}
