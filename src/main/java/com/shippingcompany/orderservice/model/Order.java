package com.shippingcompany.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private String id;
    private String customer;
    private String status;
    private String createdAt;
    private double total;
}
