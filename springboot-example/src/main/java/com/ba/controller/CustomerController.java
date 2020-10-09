package com.ba.controller;

import com.ba.domain.Customer;
import com.ba.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }
}
