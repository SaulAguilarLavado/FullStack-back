package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.Venue;
import java.util.Collection;

public interface VenueRepository {
    Venue findById(String id);
    Collection<Venue> findAll();
    Venue save(Venue venue);
    void deleteById(String id);
}
