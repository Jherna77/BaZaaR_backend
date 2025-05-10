package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.model.Order;

public interface UpdateOrderUseCase {

    Optional<Order> updateOrder(Order order) throws OrderException;

    Optional<Order> addItemToOrder(Long orderId, Long productId) throws OrderException;

    Optional<Order> removeItemFromOrder(Long orderId, Long productId) throws OrderException;

    Optional<Order> updateItemQuantity(Long orderId, Long productId, Integer newQuantity) throws OrderException;

}
