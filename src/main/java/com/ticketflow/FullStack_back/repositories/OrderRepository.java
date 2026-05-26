package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.Order;
import java.util.Collection;

public interface OrderRepository {
    Order findById(String id);
    Collection<Order> findAll();
    Collection<Order> findByUserId(String userId);
    Order save(Order order);
    void deleteById(String id);
}
