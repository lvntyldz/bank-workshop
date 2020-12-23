package com.ba.controller;

import com.ba.domain.Customer;
import com.ba.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Customer customer = service.getCustomerById(id);

        customer.getOrders().forEach(o->{
            System.out.println(o.getId());
        });

        return customer;
    }

    @GetMapping("/{id}/{newName}")
    public Customer updateCustomerNameById(@PathVariable Long id, @PathVariable String newName) {
        return service.uodateCustomerNameById(id, newName);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return service.deleteCustomerById(id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllCustomer() {
        service.deleteAllCustomers();
        return "Tüm kayıtlar silindi";
    }
}
