package com.microshop.payment.port;

import com.microshop.payment.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "customerPort", url = "${account.url}")
public interface CustomerPort {

    @GetMapping("/customers/{id}")
    CustomerResponse getById(@PathVariable Long id, @RequestHeader("Cookie") String session);
}
