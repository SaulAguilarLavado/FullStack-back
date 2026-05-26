package com.ticketflow.FullStack_back.dto.order;

public class OrderItemRequest {
    private String ticketTypeId;
    private int quantity;

    public OrderItemRequest() {}

    public OrderItemRequest(String ticketTypeId, int quantity) {
        this.ticketTypeId = ticketTypeId;
        this.quantity = quantity;
    }

    public String getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
