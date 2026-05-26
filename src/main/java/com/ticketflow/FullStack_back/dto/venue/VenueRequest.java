package com.ticketflow.FullStack_back.dto.venue;

public class VenueRequest {
    private String name;
    private String address;
    private String city;
    private int capacity;

    public VenueRequest() {}

    public VenueRequest(String name, String address, String city, int capacity) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
