package com.ticketflow.FullStack_back.dto.event;

import java.time.LocalDateTime;

public class EventRequest {
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String venueId;
    private String ticketTypeId;
    private int availableSeats;

    public EventRequest() {}

    public EventRequest(String title, String description, LocalDateTime dateTime, String venueId, String ticketTypeId, int availableSeats) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.venueId = venueId;
        this.ticketTypeId = ticketTypeId;
        this.availableSeats = availableSeats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
