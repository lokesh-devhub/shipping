package com.shippingcompany.orderservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shippingcompany.orderservice.client.InvoiceClient;
import com.shippingcompany.orderservice.client.PaymentClient;
import com.shippingcompany.orderservice.kafka.OrderEventProducer;
import com.shippingcompany.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class OrderService {
    private final List<Order> orders = new CopyOnWriteArrayList<>();
    private final PaymentClient paymentClient;

    private final InvoiceClient invoiceClient;
    @Autowired
    private OrderEventProducer orderEventProducer;

    public Order createOrder(Order order) throws JsonProcessingException {
        // Save the order to DB
//        Order savedOrder = orderRepository.save(order);

        // Serialize and send event
        String eventPayload = new ObjectMapper().writeValueAsString(order);
        orderEventProducer.sendOrderEvent(eventPayload);

        return order;
    }

    public OrderService(PaymentClient paymentClient, InvoiceClient invoiceClient) {
        this.paymentClient = paymentClient;
        this.invoiceClient = invoiceClient;
        orders.addAll(List.of(
                new Order("ORD001", "Alice", "Pending", LocalDateTime.now().minusDays(1).toString(), 249.99),
                new Order("ORD002", "Bob", "Paid", LocalDateTime.now().minusDays(2).toString(), 129.99),
                new Order("ORD003", "Eve", "Failed", LocalDateTime.now().minusDays(3).toString(), 199.49)
        ));
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void retryOrder(String id) {
        orders.stream()
                .filter(o -> o.getId().equals(id) && o.getStatus().equals("Failed"))
                .findFirst()
                .ifPresent(o -> {
                    var payment = paymentClient.pay(o.getId(), o.getTotal());
                    o.setStatus(payment.getStatus().equals("SUCCESS") ? "Paid" : "Failed");

                    if(payment.getStatus().equals("SUCCESS")){
                        var invoice = invoiceClient.generateInvoice(o.getId(), o.getTotal());
                        System.out.println("Invoice generated: "+invoice);
                    }
                });
    }
}
