package com.alessandropaiano.gamingeshop.service;


import com.alessandropaiano.gamingeshop.model.OrderItem;
import com.alessandropaiano.gamingeshop.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Integer id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(OrderItem updatedOrderItem) {
        return orderItemRepository.save(updatedOrderItem);
    }

    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }
}

