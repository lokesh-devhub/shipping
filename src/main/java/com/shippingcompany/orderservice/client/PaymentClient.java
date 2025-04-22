package com.shippingcompany.orderservice.client;

import com.shippingcompany.orderservice.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment", url = "http://localhost:8081")
public interface PaymentClient {
    @PostMapping
    @CircuitBreaker(name = "paymentservice", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentServiceRetry", fallbackMethod = "paymentRetryFallback")
    Payment pay(@PathVariable String orderId, @RequestParam double amount);

    default Payment paymentRetryFallback(String orderId, double amount, Throwable ex) {
        System.out.println("Retry fallback triggered for PaymentService. Cause: " + ex.getMessage());
        return new Payment(orderId, "RETRY_FAILED", amount);
    }

    default Payment paymentFallback(String orderId, double amount, Throwable ex){
        System.out.println("Exception processing payment: "+ex.getMessage());
        return new Payment(orderId, "FAILED", amount);
    }
}
