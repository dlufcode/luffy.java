package com.luffy.view.service.db;

import com.luffy.view.domain.Order;

import java.util.List;

public interface DbService {
    void addOrder(Order order);
    List<Order> getOrderList();
}
