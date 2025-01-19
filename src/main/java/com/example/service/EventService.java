package com.example.service;
import com.example.dto.EventCreationDTO;
import com.example.entity.Event;
import com.example.entity.Ticket;
import com.example.entity.Place;
import com.example.repository.EventRepository;
import com.example.repository.PlaceRepository;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PlaceRepository placeRepository;
    public Event createEvent(EventCreationDTO eventDTO) {
        Place place = placeRepository.findByName(eventDTO.getPlaceDTO().getName())
                .orElseGet(() -> new Place(eventDTO.getPlaceDTO().getName(), eventDTO.getPlaceDTO().getAddress()));
        Event event = new Event();
        event.setEventDate(LocalDateTime.parse(eventDTO.getEventDate()));
        event.setName(eventDTO.getName());
        event.setPlace(place);
        eventRepository.save(event);
        eventDTO.getTicketPackDTOList().forEach(ticketPack -> {
            for (int i = 0; i < ticketPack.getCount(); i++) {
                Ticket ticket = new Ticket(ticketPack.getCost(), "Seat " + (i + 1), event);
                ticketRepository.save(ticket);
            }
        });
        return event;
    }
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}

