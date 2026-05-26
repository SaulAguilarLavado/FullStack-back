package com.ticketflow.FullStack_back.dto.ticket;

public class TicketRequest {
    private String eventId;
    private String ticketTypeId;
    private String purchaserEmail;
    private int quantity;

    public TicketRequest() {}

    public TicketRequest(String eventId, String ticketTypeId, String purchaserEmail, int quantity) {
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.purchaserEmail = purchaserEmail;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
