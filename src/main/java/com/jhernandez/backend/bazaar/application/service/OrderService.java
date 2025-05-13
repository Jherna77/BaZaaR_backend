package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.domain.model.User;

public class OrderService implements OrderServicePort {

    private final UserRepositoryPort userRepository;
    private final OrderRepositoryPort orderRepository;

    public OrderService (UserRepositoryPort userRepository, OrderRepositoryPort orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrderFromCart(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException("User ID cannot be null");
        }
        User existingUser = userRepository.findUserById(userId)
            .orElseThrow(() -> new UserException("User not found"));
        
        existingUser.createOrderFromCart();
        userRepository.saveUser(existingUser);
    }

    // @Override
    // public List<Order> findAllOrders() {
    //     return orderRepository.findAllOrders();
    // }

    @Override
    public List<Order> findOrdersByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException("User ID cannot be null");
        }
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"))
                .getOrders();
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return orderRepository.findOrderById(id)
            .orElseThrow(() -> new OrderException("Order not found"));
    }

    // @Override
    // public void updateOrder(Order order) throws OrderException {
    //     // TODO
    //     orderRepository.saveOrder(order);
    // }

    // @Override
    // public void deleteOrderById(Long orderId) throws OrderException {
    //     if (orderId == null) {
    //         throw new OrderException("Order ID cannot be null");
    //     }
    //     if (!orderRepository.existsById(orderId)) {
    //         throw new OrderException("Order not found");
    //     }
    //     orderRepository.deleteOrderById(orderId);
    // }


}
