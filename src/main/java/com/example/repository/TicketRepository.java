package com.example.repository;
import com.example.entity.Ticket;
import com.example.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventIdAndStatus(Long eventId, TicketStatus status);
    List<Ticket> findByCustomerId(Long customerId);
}