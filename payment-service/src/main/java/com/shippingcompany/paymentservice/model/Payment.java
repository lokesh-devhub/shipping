package com.shippingcompany.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
    private String orderId;
    private String status; // SUCCESS / FAILED
    private double amount;
}
