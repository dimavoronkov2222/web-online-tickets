package com.example.service;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
    @Test
    void createCustomer() {
        Customer customer = new Customer("John", "john@example.com", "123456789");
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Customer createdCustomer = customerService.createCustomer(customer);
        assertNotNull(createdCustomer);
        assertEquals("John", createdCustomer.getName());
        assertEquals("john@example.com", createdCustomer.getEmail());
    }
    @Test
    void getCustomerById() {
        Customer customer = new Customer("John", "john@example.com", "123456789");
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        Customer foundCustomer = customerService.getCustomerById(1L);
        assertNotNull(foundCustomer);
        assertEquals(1L, foundCustomer.getId());
    }
}