package com.server.service;

public interface ShippingService {
    boolean arrangeShipment(String orderId);
    void cancelShipment(String orderId);
}
