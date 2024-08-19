package com.example.customer_service.converter;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverterDto {

    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
        return customerDto;
    }
    public Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.id(),
                customerDto.name(),
                customerDto.email(),
                customerDto.phoneNumber()
        );
        return customer;
    }
}
