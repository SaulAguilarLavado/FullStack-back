package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.Ticket;
import java.util.Collection;

public interface TicketRepository {
    Ticket findById(String id);
    Collection<Ticket> findAll();
    Collection<Ticket> findByEventId(String eventId);
    Ticket save(Ticket ticket);
    void deleteById(String id);
}
