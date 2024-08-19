package com.example.order_service.dto;

public record BookDto(
        Long id,
        String title,
        String author,
        int price,
        int stock
) {
}
