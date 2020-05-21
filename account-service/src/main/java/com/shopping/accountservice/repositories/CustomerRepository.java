package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CustomerRepository implements ICustomerRepository {

    private Set<Customer> customers;

    public CustomerRepository() {
        customers = new HashSet<>();
        customers.add(new Customer(1, "Zorkov"));
        customers.add(new Customer(2, "Lorne"));
        customers.add(new Customer(3, "Matthias"));
    }

    @Override
    public Set<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(final int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny()
                .orElse(null);
    }
}
