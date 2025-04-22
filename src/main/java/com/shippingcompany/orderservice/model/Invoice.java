package com.shippingcompany.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Invoice {
    private String invoiceId;
    private String orderId;
    private String issuedAt;
    private double amount;
}