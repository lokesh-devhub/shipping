package com.shippingcompany.orderservice.controller;


import com.shippingcompany.orderservice.model.Order;
import com.shippingcompany.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/{id}/retry")
    public void retryOrder(@PathVariable String id) {
        orderService.retryOrder(id);
    }
}
