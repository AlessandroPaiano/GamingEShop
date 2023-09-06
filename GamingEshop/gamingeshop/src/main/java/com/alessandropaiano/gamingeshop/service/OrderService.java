package com.alessandropaiano.gamingeshop.service;

import com.alessandropaiano.gamingeshop.model.Order;

import com.alessandropaiano.gamingeshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order updatedOrder) {
        return orderRepository.save(updatedOrder);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
