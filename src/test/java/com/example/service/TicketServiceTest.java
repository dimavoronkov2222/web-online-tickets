package com.example.service;
import com.example.entity.Ticket;
import com.example.entity.Customer;
import com.example.enums.TicketStatus;
import com.example.repository.TicketRepository;
import com.example.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private TicketService ticketService;
    @Test
    void getAvailableTickets() {
        Ticket ticket = new Ticket(10.0, "Seat 1", null);
        ticket.setStatus(TicketStatus.FREE);
        when(ticketRepository.findByEventIdAndStatus(1L, TicketStatus.FREE)).thenReturn(List.of(ticket));
        List<Ticket> availableTickets = ticketService.getAvailableTickets(1L);
        assertNotNull(availableTickets);
        assertEquals(1, availableTickets.size());
        assertEquals(TicketStatus.FREE, availableTickets.get(0).getStatus());
    }
    @Test
    void assignTicketToCustomer() {
        Ticket ticket = new Ticket(10.0, "Seat 1", null);
        ticket.setId(1L);
        ticket.setStatus(TicketStatus.FREE);
        Customer customer = new Customer("John", "john@example.com", "123456789");
        customer.setId(1L);
        when(ticketRepository.findById(1L)).thenReturn(java.util.Optional.of(ticket));
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        Ticket assignedTicket = ticketService.assignTicketToCustomer(1L, 1L);
        assertNotNull(assignedTicket);
        assertEquals(TicketStatus.SOLD, assignedTicket.getStatus());
        assertEquals(1L, assignedTicket.getCustomer().getId());
    }
}