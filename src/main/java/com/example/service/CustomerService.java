package com.example.service;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}