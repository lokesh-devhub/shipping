package com.shippingcompany.invoiceservice.controller;


import com.shippingcompany.invoiceservice.model.Invoice;
import com.shippingcompany.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/{orderId}")
    public Invoice generate(@PathVariable String orderId, @RequestParam double amount) {
        return invoiceService.generateInvoice(orderId, amount);
    }
}
