package com.ticketflow.FullStack_back.models;

public class OrderItem {
    private String id;
    private String ticketId;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(String id, String ticketId, int quantity, double price) {
        this.id = id;
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
