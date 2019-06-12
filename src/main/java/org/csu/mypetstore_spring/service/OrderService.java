package org.csu.mypetstore_spring.service;

import org.csu.mypetstore_spring.domain.Order;

import java.util.List;

public interface OrderService {
    void insertOrder(Order order);//插入新订单
    Order getOrder(int orderId);//根据订单 ID 查找订单
    List<Order> getOrdersByUsername(String username);//根据用户名查找订单
}
