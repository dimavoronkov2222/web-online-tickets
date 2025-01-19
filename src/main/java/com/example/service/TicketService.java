package com.example.service;
import com.example.entity.Ticket;
import com.example.entity.Customer;
import com.example.enums.TicketStatus;
import com.example.repository.TicketRepository;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public List<Ticket> getAvailableTickets(Long eventId) {
        return ticketRepository.findByEventIdAndStatus(eventId, TicketStatus.FREE);
    }
    public Ticket assignTicketToCustomer(Long ticketId, Long customerId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        if (!ticket.getStatus().equals(TicketStatus.FREE)) {
            throw new RuntimeException("Ticket already sold");
        }
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        ticket.setCustomer(customer);
        ticket.setStatus(TicketStatus.SOLD);
        ticketRepository.save(ticket);
        return ticket;
    }
}