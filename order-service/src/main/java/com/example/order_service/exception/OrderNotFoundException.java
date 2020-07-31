package com.example.order_service.exception;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
