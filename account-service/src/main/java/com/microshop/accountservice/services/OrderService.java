package com.microshop.accountservice.services;

import com.microshop.accountservice.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("order-s")
public interface OrderService {

    @GetMapping("/orders/ofCustomer/{customerId}")
    List<OrderResponse> findByCustomerId(@PathVariable final Long customerId);
}
