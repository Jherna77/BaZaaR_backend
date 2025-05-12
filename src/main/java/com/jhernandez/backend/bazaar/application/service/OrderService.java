package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.domain.model.Product;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepository;
    private final ProductRepositoryPort productRepository;

    public OrderService (OrderRepositoryPort orderRepository, ProductRepositoryPort productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
        // TODO
        return orderRepository.saveOrder(order);
    }

    @Override
    public Optional<Order> addItemToOrder(Long orderId, Long productId) throws OrderException {
        Order existingOrder = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderException("Order not found"));
        if (existingOrder.getItems().stream().anyMatch(item -> item.getProduct().getId().equals(productId))) {
            throw new OrderException("Item already exists in the order");
        }
        Product product = productRepository.findProductById(productId)
                .orElseThrow(() -> new OrderException("Product not found"));
        // existingOrder.addItem(product);
        return orderRepository.saveOrder(existingOrder);
    }

    @Override
    public Optional<Order> removeItemFromOrder(Long orderId, Long productId) throws OrderException {
        Order existingOrder = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderException("Order not found"));
        existingOrder.removeItem(productId);
        return orderRepository.saveOrder(existingOrder);
    }

    @Override
    public Optional<Order> updateItemQuantity(Long orderId, Long productId, Integer newQuantity) throws OrderException {
        Order existingOrder = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderException("Order not found"));
        existingOrder.updateItemQuantity(productId, newQuantity);
        return orderRepository.saveOrder(existingOrder);
    }

    @Override
    public void deleteOrderById(Long orderId) throws OrderException {
        if (orderId == null) {
            throw new OrderException("Order ID cannot be null");
        }
        if (!orderRepository.existsById(orderId)) {
            throw new OrderException("Order not found");
        }
        orderRepository.deleteOrderById(orderId);
    }

}
