package com.alessandropaiano.gamingeshop.repository;

import com.alessandropaiano.gamingeshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
