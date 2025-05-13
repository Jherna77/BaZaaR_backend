package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createOrderFromCart(@PathVariable Long userId) {
        log.info("Creating order");
        try {
            orderService.createOrderFromCart(userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DomainException e) {
            log.error("Error creating order");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @GetMapping
    // public List<OrderDto> findAllOrders() {
    //     log.info("Finding all orders");
    //     return orderService.findAllOrders().stream()
    //         .map(orderDtoMapper::toDto)
    //         .collect(Collectors.toList());
    // }

    @GetMapping("/user/{userId}")
    public List<OrderDto> findOrdersByUserId(@PathVariable Long userId) {
        log.info("Finding all orders for the user with ID ", userId);
        try {
            return orderService.findOrdersByUserId(userId).stream()
                .map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
        } catch (DomainException e) {
            log.error("Error finding orders for user with ID {}", userId);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id) {
        log.info("Finding order with ID {}", id);
        try {
            return ResponseEntity.ok(orderDtoMapper.toDto(orderService.findOrderById(id)));
        } catch (DomainException e) {
            log.error("Error getting order with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDto order) {
    //     log.info("Updating order with ID {}", id);
    //     try {
    //         orderService.updateOrder(orderDtoMapper.toDomain(order));
    //         return ResponseEntity.ok().build();
    //     } catch (DomainException e) {
    //         log.error("Error updating order with ID {}", id);
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
    //     log.info("Deleting order with ID {}", id);
    //     try {
    //         orderService.deleteOrderById(id);
    //         return ResponseEntity.ok().build();
    //     } catch (DomainException e) {
    //         log.error("Error deleting order with ID {}", id);
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

}
