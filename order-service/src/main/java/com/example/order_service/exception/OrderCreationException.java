package com.example.order_service.exception;

public class OrderCreationException extends Exception {
    public OrderCreationException() {
    }

    public OrderCreationException(String message) {
        super(message);
    }
}
