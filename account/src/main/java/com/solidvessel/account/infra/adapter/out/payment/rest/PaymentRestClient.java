package com.solidvessel.account.infra.adapter.out.payment.rest;

import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "paymentRestClient", url = "${payment.url}")
public interface PaymentRestClient {

    @GetMapping("/payments/ofCustomer/{customerId}")
    List<PaymentDataModel> getByCustomerId(@PathVariable final Long customerId, @RequestHeader("Cookie") String session);
}
