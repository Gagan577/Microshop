package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.entity.Product;
import com.shopping.paymentservice.services.ICustomerRemoteService;
import com.shopping.paymentservice.services.IPaymentService;
import com.shopping.paymentservice.services.IProductRemoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private IPaymentService paymentService;
    private IProductRemoteService productRemoteService;
    private ICustomerRemoteService customerRemoteService;

    public PaymentRestController(final IPaymentService paymentService, final IProductRemoteService productRemoteService, final ICustomerRemoteService customerRemoteService) {
        this.paymentService = paymentService;
        this.productRemoteService = productRemoteService;
        this.customerRemoteService = customerRemoteService;
    }

    @GetMapping("/")
    public Set<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable("paymentId") final String id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/withIds")
    public List<Payment> getPaymentsByIds(@RequestParam("paymentIds") final List<String> paymentIds) {
        return paymentService.getPaymentsByIds(paymentIds);
    }

    @GetMapping("/{paymentId}/products")
    public List<Product> getProductsOfPayment(@PathVariable("paymentId") final String id) {
        return productRemoteService.getProductsOfPayment(id);
    }

    @GetMapping("/{paymentId}/customer")
    public Customer getCustomerOfPayment(@PathVariable("paymentId") final String id) {
        return customerRemoteService.getCustomerById(id);
    }
}
