package com.alessandropaiano.gamingeshop.controller;

import com.alessandropaiano.gamingeshop.dto.OrderDTO;
import com.alessandropaiano.gamingeshop.model.Order;
import com.alessandropaiano.gamingeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order createdOrder = orderService.saveOrder(order);

        OrderDTO createdOrderDTO = modelMapper.map(createdOrder, OrderDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        orderDTO.setId(id); // Assumendo che l'ID non possa essere modificato
        Order order = modelMapper.map(orderDTO, Order.class);
        Order updatedOrder = orderService.updateOrder(order);

        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }

        OrderDTO updatedOrderDTO = modelMapper.map(updatedOrder, OrderDTO.class);
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
