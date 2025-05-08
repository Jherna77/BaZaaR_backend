package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.model.Order;

public interface CreateOrderUseCase {

    Optional<Order> createOrder(Order order) throws OrderException;

}
