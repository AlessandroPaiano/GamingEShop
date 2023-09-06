package com.alessandropaiano.gamingeshop.repository;

import com.alessandropaiano.gamingeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}

