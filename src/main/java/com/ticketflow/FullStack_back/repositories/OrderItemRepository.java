package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.OrderItem;
import java.util.Collection;

public interface OrderItemRepository {
    OrderItem findById(String id);
    Collection<OrderItem> findAll();
    OrderItem save(OrderItem item);
    void deleteById(String id);
}
