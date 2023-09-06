package com.alessandropaiano.gamingeshop.DAOFactory;

import com.alessandropaiano.gamingeshop.repository.UserRepository;
import com.alessandropaiano.gamingeshop.repository.ProductRepository;
import com.alessandropaiano.gamingeshop.repository.OrderRepository;
import com.alessandropaiano.gamingeshop.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DAOFactory(UserRepository userRepository, ProductRepository productRepository,
                      OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public OrderItemRepository getOrderItemRepository() {
        return orderItemRepository;
    }
}
