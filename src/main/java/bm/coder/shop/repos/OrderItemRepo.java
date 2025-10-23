package bm.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bm.coder.shop.models.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
