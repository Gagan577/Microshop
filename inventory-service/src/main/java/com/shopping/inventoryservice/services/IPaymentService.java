package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Payment;

public interface IPaymentService {

    Payment findPaymentOfProduct(Long productId);
}
