package com.server.service;

public interface InventoryService {
    boolean reserveItems(String orderId);
    void cancelReservation(String orderId);
}
