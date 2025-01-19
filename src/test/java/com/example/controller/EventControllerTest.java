package com.example.controller;
import com.example.dto.EventCreationDTO;
import com.example.entity.Event;
import com.example.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController eventController;
    @Test
    void createEvent() {
        EventCreationDTO eventDTO = new EventCreationDTO();
        Event event = new Event();
        when(eventService.createEvent(Mockito.any(EventCreationDTO.class))).thenReturn(event);
        ResponseEntity<Event> response = eventController.createEvent(eventDTO);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
    @Test
    void getEventById() {
        Event event = new Event();
        event.setId(1L);
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<Event> response = eventController.getEventById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }
    @Test
    void getAllEvents() {
        List<Event> events = List.of(new Event(), new Event());
        when(eventService.getAllEvents()).thenReturn(events);
        ResponseEntity<List<Event>> response = eventController.getAllEvents();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
}