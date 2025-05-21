package com.jhernandez.backend.bazaar.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
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
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        User customer = userRepository.findUserById(userId)
            .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        
        if (customer.getCart().isEmpty()) {
            throw new UserException(ErrorCode.CART_EMPTY);
        }

        List<Order> newOrders = new ArrayList<>();
        customer.getCart().forEach(item -> {
            newOrders.add(new Order(
                null,
                item,
                // item.clone(),
                customer,
                LocalDateTime.now()));
        });

        processOrders(newOrders, customer);
    }

    @Override
    public List<Order> findPurchaseOrdersByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getPurchaseOrders();
    }

    @Override
    public List<Order> findSaleOrdersByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getSaleOrders();
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return orderRepository.findOrderById(id)
            .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));
    }

    public void processOrders(List<Order> newOrders, User customer) {
        Set<User> sellersToUpdate = new HashSet<>();

        User seller = new User();
        for (Order order : newOrders) {
            seller = userRepository.findUserById(order.getItem().getProduct().getOwner().getId())
                        .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
            customer.addPurchaseOrder(order);
            seller.addSaleOrder(order);
            sellersToUpdate.add(seller);
        }

        customer.clearCart();

        userRepository.saveUser(customer);

        for (User userSeller : sellersToUpdate) {
            userRepository.saveUser(userSeller);
        }

    }

}
