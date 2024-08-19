package com.example.customer_service.service;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.exception.CustomerNotFoundException;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer getCustomerById(long id){
        Optional<Customer> existingCustomer=customerRepository.findById(id);
        if (existingCustomer.isPresent()){
            return existingCustomer.get();
        }else {
            throw new CustomerNotFoundException("Customer is Not Found This Id:"+id);
        }
    }
    public void deleteCustomerById(long id){
        Optional<Customer> existingCustomer=customerRepository.findById(id);
        if (existingCustomer.isPresent()){
            customerRepository.deleteById(id);
        }else {
            throw new CustomerNotFoundException("Customer is Not Found This Id:"+id);
        }
    }
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(long id,Customer customer){
        Optional<Customer> existingCustomer=customerRepository.findById(id);
        if (existingCustomer.isPresent()){
            existingCustomer.get().setName(customer.getName());
            existingCustomer.get().setEmail(customer.getEmail());
            existingCustomer.get().setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(existingCustomer.get());
        }else {
            throw new CustomerNotFoundException("Customer is Not Found This Id:"+id);
        }
    }
}
