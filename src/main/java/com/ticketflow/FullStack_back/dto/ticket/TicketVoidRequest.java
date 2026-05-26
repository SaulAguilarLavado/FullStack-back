package com.ticketflow.FullStack_back.dto.ticket;

public class TicketVoidRequest {
    private String ticketId;

    public TicketVoidRequest() {}

    public TicketVoidRequest(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
