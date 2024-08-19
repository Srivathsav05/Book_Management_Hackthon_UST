package com.example.order_service.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String s) {
        super(s);
    }
}
