package com.ba.service;

import com.ba.domain.Customer;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Cacheable("cutomerData")
    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (!optionalCustomer.isPresent()) {
            return null;
        }

        return optionalCustomer.get();
    }
}
