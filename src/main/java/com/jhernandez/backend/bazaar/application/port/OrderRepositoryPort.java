package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Order;

public interface OrderRepositoryPort {

    Optional<Order> saveOrder(Order order);

    List<Order> findAllOrders();

    Optional<Order> findOrderById(Long id);

    Boolean existsById(Long id);

    void deleteOrderById(Long id);

}
