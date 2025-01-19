package com.example.dto;
import java.util.List;
public class EventCreationDTO {
    private String eventDate;
    private String name;
    private List<TicketPackDTO> ticketPackDTOList;
    private PlaceDTO placeDTO;
    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<TicketPackDTO> getTicketPackDTOList() {
        return ticketPackDTOList;
    }
    public void setTicketPackDTOList(List<TicketPackDTO> ticketPackDTOList) {
        this.ticketPackDTOList = ticketPackDTOList;
    }
    public PlaceDTO getPlaceDTO() {
        return placeDTO;
    }
    public void setPlaceDTO(PlaceDTO placeDTO) {
        this.placeDTO = placeDTO;
    }
}