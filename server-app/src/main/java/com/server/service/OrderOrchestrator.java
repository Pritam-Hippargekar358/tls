package com.server.service;

public class OrderOrchestrator {
    private InventoryService inventoryService;
    private PaymentService paymentService;
    private ShippingService shippingService;

    public OrderOrchestrator(InventoryService inventoryService, PaymentService paymentService, ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
    }

    public void processOrder(String orderId) {
        try {
            //handle various edge cases and exceptions more robustly.
            // Step 1: Reserve items
            if (!inventoryService.reserveItems(orderId)) {
                throw new Exception("Failed to reserve items");
            }

            // Step 2: Process payment
            if (!paymentService.processPayment(orderId)) {
                throw new Exception("Failed to process payment");
            }

            // Step 3: Arrange shipment
            if (!shippingService.arrangeShipment(orderId)) {
                throw new Exception("Failed to arrange shipment");
            }

            System.out.println("Order processed successfully: " + orderId);
        } catch (Exception e) {
            System.out.println("Order processing failed: " + e.getMessage());
            // Compensation logic
            compensateOrder(orderId);
        }
    }

    private void compensateOrder(String orderId) {
        // Cancel shipment if it was arranged
        shippingService.cancelShipment(orderId);

        // Refund payment if it was processed
        paymentService.refundPayment(orderId);

        // Cancel item reservation if it was made
        inventoryService.cancelReservation(orderId);

        System.out.println("Compensation completed for order: " + orderId);
    }
}
