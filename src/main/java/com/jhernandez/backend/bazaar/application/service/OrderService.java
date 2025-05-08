package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.model.Order;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepository;

    public OrderService (OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> createOrder(Order order) throws OrderException {
        return orderRepository.saveOrder(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    @Override
    public Optional<Order> findOrderById(Long id) throws OrderException {
        return orderRepository.findOrderById(id);
    }

    @Override
    public Optional<Order> updateOrder(Order order) throws OrderException {
        return orderRepository.saveOrder(order);
    }

}
