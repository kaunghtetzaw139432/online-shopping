package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.OrderNotFoundException;
import bm.coder.shop.models.Order;
import bm.coder.shop.models.OrderItem;
import bm.coder.shop.repos.OrderItemRepo;
import bm.coder.shop.repos.OrderRepo;
import bm.coder.shop.services.OrderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    @Autowired
    private final OrderRepo orderRepo;
    @Autowired
    private final OrderItemRepo orderItemRepo;

    @Override
    public void add(Order order) {
        orderRepo.save(order);

    }

    @Override
    public Order get(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("No order with that id"));
    }

    @Override
    public List<Order> all() {
        return orderRepo.findAll();
    }

    @Override
    public OrderItem addItem(OrderItem orderItem) {
       return orderItemRepo.save(orderItem);
    }

    @Override
    public List<Order> getByOrder(Long buyerId) {
        return orderRepo.findBybuyerId(buyerId);
    }

}
