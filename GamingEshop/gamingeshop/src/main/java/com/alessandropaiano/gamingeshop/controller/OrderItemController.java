package com.alessandropaiano.gamingeshop.controller;

import com.alessandropaiano.gamingeshop.dto.OrderItemDTO;
import com.alessandropaiano.gamingeshop.model.OrderItem;
import com.alessandropaiano.gamingeshop.service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, ModelMapper modelMapper) {
        this.orderItemService = orderItemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItem(@PathVariable Integer id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        if (orderItem == null) {
            return ResponseEntity.notFound().build();
        }

        OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);
        return ResponseEntity.ok(orderItemDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getOrderItems();
        List<OrderItemDTO> orderItemDTOs = orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderItemDTOs);
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
        OrderItem createdOrderItem = orderItemService.saveOrderItem(orderItem);

        OrderItemDTO createdOrderItemDTO = modelMapper.map(createdOrderItem, OrderItemDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDTO) {
        orderItemDTO.setId(id); // Assumendo che l'ID non possa essere modificato
        OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);

        if (updatedOrderItem == null) {
            return ResponseEntity.notFound().build();
        }

        OrderItemDTO updatedOrderItemDTO = modelMapper.map(updatedOrderItem, OrderItemDTO.class);
        return ResponseEntity.ok(updatedOrderItemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
