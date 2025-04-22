package com.shippingcompany.paymentservice.controller;


import com.shippingcompany.paymentservice.model.Payment;
import com.shippingcompany.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public Payment pay(@PathVariable String orderId, @RequestParam double amount) {
        return paymentService.processPayment(orderId, amount);
    }
}
