package com.example.controller;
import com.example.entity.Ticket;
import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @GetMapping("/available/{eventId}")
    public ResponseEntity<List<Ticket>> getAvailableTickets(@PathVariable Long eventId) {
        List<Ticket> availableTickets = ticketService.getAvailableTickets(eventId);
        return ResponseEntity.ok(availableTickets);
    }
    @PostMapping("/assign/{ticketId}/{customerId}")
    public ResponseEntity<Ticket> assignTicketToCustomer(@PathVariable Long ticketId, @PathVariable Long customerId) {
        Ticket ticket = ticketService.assignTicketToCustomer(ticketId, customerId);
        return ResponseEntity.ok(ticket);
    }
}