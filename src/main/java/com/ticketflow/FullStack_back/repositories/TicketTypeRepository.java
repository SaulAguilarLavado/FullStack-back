package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.TicketType;
import java.util.Collection;

public interface TicketTypeRepository {
    TicketType findById(String id);
    Collection<TicketType> findAll();
    TicketType save(TicketType ticketType);
    void deleteById(String id);
}
