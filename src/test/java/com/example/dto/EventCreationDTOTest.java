package com.example.dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class EventCreationDTOTest {
    @Test
    void testEventCreationDTO() {
        EventCreationDTO eventDTO = new EventCreationDTO();
        eventDTO.setEventDate("2025-02-01T12:00:00");
        eventDTO.setName("Concert");
        assertEquals("2025-02-01T12:00:00", eventDTO.getEventDate());
        assertEquals("Concert", eventDTO.getName());
    }
}