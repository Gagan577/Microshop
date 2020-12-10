package com.microshop.accountservice.services;

import com.microshop.accountservice.entity.Payment;
import com.microshop.accountservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private final IRemoteRequestService requestService;

    public PaymentService(final IRemoteRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<Payment> findByCustomerId(final Long customerId) {
        Payment[] payments = requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Payment[].class)
                .send();
        return Arrays.asList(payments);
    }
}
