package com.example.order_service.clients;

import com.example.order_service.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service",url = "localhost:8100/api/v1/books")
public interface BookServiceClient {
    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable("id") Long id);
}
