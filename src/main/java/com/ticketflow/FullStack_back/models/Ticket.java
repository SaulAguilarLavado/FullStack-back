package com.ticketflow.FullStack_back.models;

public class Ticket {
    private String id;
    private String eventId;
    private String ticketTypeId;
    private String purchaserEmail;
    private double price;
    private String status;

    public Ticket() {}

    public Ticket(String id, String eventId, String ticketTypeId, String purchaserEmail, double price, String status) {
        this.id = id;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.purchaserEmail = purchaserEmail;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getPurchaserEmail() {
        return purchaserEmail;
    }

    public void setPurchaserEmail(String purchaserEmail) {
        this.purchaserEmail = purchaserEmail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
