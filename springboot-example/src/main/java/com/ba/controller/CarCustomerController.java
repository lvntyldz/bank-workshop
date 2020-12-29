package com.ba.controller;

import com.ba.domain.CarCustomer;
import com.ba.service.AppCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car-customer")
public class CarCustomerController {

    @Autowired
    AppCustomerService service;

    @GetMapping("/add")
    public CarCustomer addCustomer() {
        return service.addNewCustomerWithoutTx();
    }

    @GetMapping("/add-v2")
    public CarCustomer addCustomerV2() {
        return service.addNewCustomer();
    }

    @GetMapping("/add-v3")
    public CarCustomer addCustomerV3() {
        return service.addNewCustomerNestedTx();
    }

}
