package com.example.customer_service.controller;

import com.example.customer_service.converter.CustomerConverterDto;
import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerConverterDto customerConverterDto;

    public CustomerController(CustomerService customerService, CustomerConverterDto customerConverterDto) {
        this.customerService = customerService;
        this.customerConverterDto = customerConverterDto;
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto dto){
        return ResponseEntity.ok(customerService.createCustomer(customerConverterDto.convertToEntity(dto)));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable long id){
        return customerConverterDto.convertToDto(customerService.getCustomerById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody CustomerDto dto){
        return ResponseEntity.ok(customerService.updateCustomer(id, customerConverterDto.convertToEntity(dto)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(Long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
