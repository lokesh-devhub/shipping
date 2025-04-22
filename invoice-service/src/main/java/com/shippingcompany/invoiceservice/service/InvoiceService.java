package com.shippingcompany.invoiceservice.service;


import com.shippingcompany.invoiceservice.model.Invoice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InvoiceService {

    public Invoice generateInvoice(String orderId, double amount) {
        return new Invoice(
                UUID.randomUUID().toString(),
                orderId,
                LocalDateTime.now().toString(),
                amount
        );
    }
}
