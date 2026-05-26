package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.Event;
import java.util.Collection;

public interface EventRepository {
    Event findById(String id);
    Collection<Event> findAll();
    Event save(Event event);
    void deleteById(String id);
}
