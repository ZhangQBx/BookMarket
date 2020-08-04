package com.book.store.api.service;

import com.book.store.api.domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {

    List<Order> getOrders(String phone,int page,int pageSize);

    Order getOrder(String phone, int status);

    boolean addOrder(Order order);

    boolean updateOrder(Order order);
}
