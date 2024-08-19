package com.example.order_service.clients;

import com.example.order_service.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",url = "localhost:8200/api/v1/customers")
public interface CustomerServiceClient {
    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") long id);
}
