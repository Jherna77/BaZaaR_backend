package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Item;
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
        User existingUser = userRepository.findUserById(userId)
            .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        
        // for (Item item : existingUser.getCart()) {
        //     if (item.getProduct().getStock() < item.getQuantity()) {
        //         throw new UserException(ErrorCode.PRODUCT_STOCK_NOT_ENOUGH);
        //     }
        // }

        User seller = new User();
        for (Item item : existingUser.getCart()) {
            seller = userRepository.findUserById(item.getProduct().getOwner().getId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
            seller.addSale(item);
            userRepository.saveUser(seller);
        }

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
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getPurchaseOrders();
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return orderRepository.findOrderById(id)
            .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));
    }

    // @Override
    // public void updateOrder(Order order) throws OrderException {
    //     
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
