package com.example.service;
import com.example.dto.EventCreationDTO;
import com.example.entity.Event;
import com.example.entity.Ticket;
import com.example.entity.Place;
import com.example.repository.EventRepository;
import com.example.repository.PlaceRepository;
import com.example.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class EventServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private PlaceRepository placeRepository;
    @InjectMocks
    private EventService eventService;
    @Test
    void createEvent() {
        EventCreationDTO eventDTO = new EventCreationDTO();
        eventDTO.setEventDate("2025-02-01T12:00:00");
        eventDTO.setName("Concert");
        Place place = new Place("Arena", "1234 Street");
        when(placeRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.of(place));
        Event event = new Event(LocalDateTime.parse("2025-02-01T12:00:00"), "Concert", place);
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event);
        Event createdEvent = eventService.createEvent(eventDTO);
        assertNotNull(createdEvent);
        assertEquals("Concert", createdEvent.getName());
        assertEquals("Arena", createdEvent.getPlace().getName());
    }
    @Test
    void getEventById() {
        Event event = new Event(LocalDateTime.parse("2025-02-01T12:00:00"), "Concert", new Place("Arena", "1234 Street"));
        event.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(java.util.Optional.of(event));
        Event foundEvent = eventService.getEventById(1L);
        assertNotNull(foundEvent);
        assertEquals(1L, foundEvent.getId());
    }
    @Test
    void getAllEvents() {
        List<Event> events = List.of(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(events);
        List<Event> foundEvents = eventService.getAllEvents();
        assertNotNull(foundEvents);
        assertEquals(2, foundEvents.size());
    }
}