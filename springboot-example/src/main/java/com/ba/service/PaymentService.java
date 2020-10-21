package com.ba.service;

import com.ba.domain.CardPayment;
import com.ba.domain.ChequePayment;
import com.ba.domain.Payment;
import com.ba.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public String addNewCardPayment() {
        CardPayment payment = new CardPayment();
        payment.setCardNo(123);
        payment.setAmount(1000);

        paymentRepository.save(payment);

        return payment.toString();
    }

    public String addChequePayment() {
        ChequePayment payment = new ChequePayment();
        payment.setChequeNo(543);
        payment.setAmount(1000);

        paymentRepository.save(payment);

        return payment.toString();
    }

    public List<Payment> findAllPaymentList() {
        return paymentRepository.findAll();
    }

    public Payment findPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (!optionalPayment.isPresent()) {
            return null;
        }

        return optionalPayment.get();
    }

    public String deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }
}
