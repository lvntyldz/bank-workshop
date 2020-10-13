package com.ba.controller;

import com.ba.domain.Payment;
import com.ba.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/card/add")
    public String addChequePayment() {
        return service.addChequePayment();
    }

    @GetMapping("/cheque/add")
    public String addCardPayment() {
        return service.addNewCardPayment();
    }

    @GetMapping("/list")
    public List<Payment> getAllPayment() {
        return service.findAllPaymentList();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return service.findPaymentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        return service.deletePaymentById(id);
    }
}
