package com.example.controller;
import com.example.entity.Ticket;
import com.example.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TicketControllerTest {
    @Mock
    private TicketService ticketService;
    @InjectMocks
    private TicketController ticketController;
    @Test
    void getAvailableTickets() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        when(ticketService.getAvailableTickets(1L)).thenReturn(tickets);
        ResponseEntity<List<Ticket>> response = ticketController.getAvailableTickets(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
    @Test
    void assignTicketToCustomer() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        when(ticketService.assignTicketToCustomer(1L, 1L)).thenReturn(ticket);
        ResponseEntity<Ticket> response = ticketController.assignTicketToCustomer(1L, 1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }
}