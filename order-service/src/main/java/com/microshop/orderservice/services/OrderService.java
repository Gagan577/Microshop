package com.microshop.orderservice.services;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.repositories.IOrderRepository;
import com.microshop.orderservice.wrapper.Customer;
import com.microshop.orderservice.wrapper.OrderDTO;
import com.microshop.orderservice.wrapper.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final ICustomerService customerService;
    private final IPaymentService paymentService;

    public OrderService(final IOrderRepository orderRepository, final ICustomerService customerService, final IPaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDTO findById(final Long id, final boolean pruned) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        if (pruned) {
            return new OrderDTO(order.getId(), order.getStatus());
        }
        Customer customer = customerService.findById(order.getCustomerId());
        Payment payment = paymentService.findById(order.getPaymentId());
        return new OrderDTO(order.getId(), order.getStatus(), customer, payment);
    }

    @Override
    public Iterable<Order> findByCustomerId(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order save(final Order order) {
        return orderRepository.save(order);
    }
}
