package com.example.customer_service.dto;

public record CustomerDto(
        long id,
        String name,
        String email,
        String phoneNumber
) {

}
