package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findByPaymentId(Long paymentId);

    void setPaymentIds(Long paymentId, List<Long> productIds);
}
