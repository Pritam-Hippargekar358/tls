package com.server.service;

public class InventoryServiceImpl implements InventoryService {
    @Override
    public boolean reserveItems(String orderId) {
        // Logic to reserve items
        System.out.println("Reserving items for order: " + orderId);
        return true; // Assume reservation is successful
    }

    @Override
    public void cancelReservation(String orderId) {
        // Logic to cancel item reservation
        System.out.println("Canceling item reservation for order: " + orderId);
    }
}
