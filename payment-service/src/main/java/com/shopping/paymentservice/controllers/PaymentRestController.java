package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.entity.Product;
import com.shopping.paymentservice.services.ICustomerService;
import com.shopping.paymentservice.services.IPaymentService;
import com.shopping.paymentservice.services.IProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private IPaymentService paymentService;
    private IProductService productService;
    private ICustomerService customerService;

    public PaymentRestController(final IPaymentService paymentService, final IProductService productService, final ICustomerService customerService) {
        this.paymentService = paymentService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable("paymentId") final long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<Payment> getPaymentsOfCustomer(@PathVariable("customerId") final long customerId) {
        return paymentService.getPaymentsOfCustomer(customerId);
    }

    @GetMapping("/{paymentId}/products")
    public List<Product> getProductsOfPayment(@PathVariable("paymentId") final long id) {
        return productService.getProductsOfPayment(id);
    }

    @GetMapping("/{paymentId}/customer")
    public Customer getCustomerOfPayment(@PathVariable("paymentId") final long id) {
        return customerService.getCustomerOfPayment(id);
    }
}
