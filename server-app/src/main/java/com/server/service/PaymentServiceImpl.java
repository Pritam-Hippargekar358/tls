package com.server.service;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean processPayment(String orderId) {
        // Logic to process payment
        System.out.println("Processing payment for order: " + orderId);
        return true; // Assume payment is successful
    }

    @Override
    public void refundPayment(String orderId) {
        // Logic to refund payment
        System.out.println("Refunding payment for order: " + orderId);
    }
}
