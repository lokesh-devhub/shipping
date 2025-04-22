package com.shippingcompany.orderservice.client;


import com.shippingcompany.orderservice.model.Invoice;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "invoice", url = "http://localhost:8082") // Adjust port as needed
public interface InvoiceClient {

    @PostMapping("/api/invoices/{orderId}")
    @CircuitBreaker(name = "invoiceService", fallbackMethod = "invoiceFallback")
    @Retry(name = "invoiceServiceRetry", fallbackMethod = "invoiceRetryFallback")
    Invoice generateInvoice(@PathVariable String orderId, @RequestParam double amount);

    default Invoice invoiceRetryFallback(String orderId, double amount, Throwable ex) {
        System.out.println("Retry fallback for InvoiceService: " + ex.getMessage());
        return new Invoice("INV_RETRY_FAILED", orderId, "FAILED", 0.0);
    }

    default Invoice invoiceFallback(String orderId, double amount, Throwable ex) {
        System.out.println("InvoiceService fallback triggered for order: " + orderId);
        return new Invoice("INV_FAILED", orderId, "N/A", 0.0);
    }
}
