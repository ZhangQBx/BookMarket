package com.book.store.api.service;

import com.book.store.api.domain.Order;
import com.book.store.api.handle.OrderHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServicesImp implements OrderService {

    //导入
    @Autowired(required = false)
    private OrderHandle orderHandle;

    @Override
    public List<Order> getOrders(String phone,int page,int pageSize) {
        int count = (page -1) * pageSize;
        return orderHandle.getOrders(phone,count,pageSize);
    }

    @Override
    public Order getOrder(String phone,int status) {
        return orderHandle.getOrder(phone,status);
    }

    @Override
    public boolean addOrder(Order order) {
        return orderHandle.addOrder(order.getPhone(),order.getBook_ids(),order.getBook_count(),
                order.getStatus(),order.getCreate_time(),order.isIs_delete())>0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderHandle.updateOrder(order.getBook_ids(),order.getBook_count(),
                order.getStatus(),order.isIs_delete(),order.getPhone())>0;
    }


}
