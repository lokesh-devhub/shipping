package com.shippingcompany.paymentservice.service;


import com.shippingcompany.paymentservice.model.Payment;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    public Payment processPayment(String orderId, double amount) {
        String status = new Random().nextBoolean() ? "SUCCESS" : "FAILED";
        return new Payment(orderId, status, amount);
    }
}
