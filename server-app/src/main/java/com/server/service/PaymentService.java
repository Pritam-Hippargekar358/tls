package com.server.service;

public interface PaymentService {
    boolean processPayment(String orderId);
    void refundPayment(String orderId);
}
