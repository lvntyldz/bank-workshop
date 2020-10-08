package com.ba.controller;

import com.ba.domain.Customer;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (!optionalCustomer.isPresent()) {
            return null;
        }

        Customer customer = optionalCustomer.get();

        return customer;
    }
}
