package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.OrderDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.OrderDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ORDERS;

import java.util.List;
import java.util.stream.Collectors;;

@RestController
@RequestMapping(ORDERS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderServicePort orderService;
    private final OrderDtoMapper orderDtoMapper;

    
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto order) {
        log.info("Creating order for user with ID {}", order.getUser().getId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderDtoMapper.toDomain(order))
                .map(orderDtoMapper::toDto));
        } catch (DomainException e) {
            log.error("Error creating order for user with ID {}", order.getUser().getId());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<OrderDto> findAllOrders() {
        log.info("Finding all orders");
        return orderService.findAllOrders().stream()
            .map(orderDtoMapper::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id) {
        log.info("Finding order with ID {}", id);
        try {
            return orderService.findOrderById(id).map(orderDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (DomainException e) {
            log.error("Error getting order with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{orderId}/addItem/{productId}")
    public ResponseEntity<?> addItemToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        log.info("Adding item to order with ID {}", orderId);
        try {
            return orderService.addItemToOrder(orderId, productId)
                .map(orderDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        } catch (DomainException e) {
            log.error("Error adding item to order with ID {}", orderId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{orderId}/removeItem/{productId}")
    public ResponseEntity<?> removeItemFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        log.info("Removing item from order with ID {}", orderId);
        try {
            return orderService.removeItemFromOrder(orderId, productId)
                .map(orderDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        } catch (DomainException e) {
            log.error("Error removing item from order with ID {}", orderId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{orderId}/{productId}/quantity")
    public ResponseEntity<?> updateItemQuantity(@PathVariable Long orderId, @PathVariable Long productId, @RequestBody Integer newQuantity) {
        log.info("Updating item quantity in order with ID {}", orderId);
        try {
            return orderService.updateItemQuantity(orderId, productId, newQuantity)
                .map(orderDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        } catch (DomainException e) {
            log.error("Error updating item quantity in order with ID {}", orderId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // public ResponseEntity<?> updateItemQuantity(@PathVariable Long orderId, Long productId, Integer newQuantity) {
    //     log.info("Updating item quantity in order with ID {}", orderId);
    //     try {
    //         return orderService.updateItemQuantity(orderId, productId, newQuantity)
    //             .map(orderDtoMapper::toDto)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    //     } catch (DomainException e) {
    //         log.error("Error updating item quantity in order with ID {}", orderId);
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long orderId) {
        log.info("Deleting order with ID {}", orderId);
        try {
            orderService.deleteOrderById(orderId);
            return ResponseEntity.noContent().build();
        } catch (DomainException e) {
            log.error("Error deleting order with ID {}", orderId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
