package com.example.controller;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    @Test
    void createCustomer() {
        Customer customer = new Customer("John", "john@example.com", "123456789");
        when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.createCustomer(customer);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John", response.getBody().getName());
    }
    @Test
    void getCustomerById() {
        Customer customer = new Customer("John", "john@example.com", "123456789");
        customer.setId(1L);
        when(customerService.getCustomerById(1L)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.getCustomerById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("john@example.com", response.getBody().getEmail());
    }
}