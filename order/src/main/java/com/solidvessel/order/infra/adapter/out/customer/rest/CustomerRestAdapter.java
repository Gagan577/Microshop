package com.solidvessel.order.infra.adapter.out.customer.rest;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.domain.customer.port.CustomerPort;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRestAdapter implements CustomerPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final CustomerRestClient customerRestClient;

    @Override
    public CustomerDataModel getCustomerOfOrder(Long customerId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerRestClient.getById(customerId, session),
                        throwable -> null);
    }
}
