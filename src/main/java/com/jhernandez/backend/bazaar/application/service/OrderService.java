package com.jhernandez.backend.bazaar.application.service;

import java.time.LocalDateTime;
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

    public OrderService(UserRepositoryPort userRepository, OrderRepositoryPort orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrderFromCart(Long userId) throws OrderException, UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }

        User customer = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        if (customer.getCart().isEmpty()) {
            throw new UserException(ErrorCode.CART_EMPTY);
        }

        User seller = new User();
        // Order order = new Order();
        for (Item item : customer.getCart()) {
            seller = userRepository.findUserById(item.getProduct().getOwner().getId())
                    .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
            
            orderRepository.saveOrder(new Order(null, item, customer, seller, LocalDateTime.now()))
                    .orElseThrow(() -> new UserException(ErrorCode.OPERATION_NOT_ALLOWED));

            // customer.addPurchaseOrder(order);
            // seller.addSaleOrder(order);

            // orderRepository.saveOrder(order); // solo una vez
            // userRepository.saveUser(seller);
        }

        // customer.clearCart();
        // userRepository.saveUser(customer);
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

}
