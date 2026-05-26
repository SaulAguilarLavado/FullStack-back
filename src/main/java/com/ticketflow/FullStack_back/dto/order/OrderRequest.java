package com.ticketflow.FullStack_back.dto.order;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {
    private String userId;
    private String eventId;
    private String purchaserEmail;
    private List<OrderItemRequest> items = new ArrayList<>();

    public OrderRequest() {}

    public OrderRequest(String userId, String eventId, String purchaserEmail, List<OrderItemRequest> items) {
        this.userId = userId;
        this.eventId = eventId;
        this.purchaserEmail = purchaserEmail;
        this.items = items;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPurchaserEmail() {
        return purchaserEmail;
    }

    public void setPurchaserEmail(String purchaserEmail) {
        this.purchaserEmail = purchaserEmail;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}
