package bm.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.Order;
import bm.coder.shop.models.OrderItem;

@Service
public interface OrderService {
    void add(Order order);

    OrderItem addItem(OrderItem orderItem);

    Order get(Long id);

    List<Order> all();

    List<Order>getByOrder(Long buyerId);

}
