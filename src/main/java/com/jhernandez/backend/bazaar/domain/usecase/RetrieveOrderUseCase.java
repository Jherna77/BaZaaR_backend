package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.model.Order;

public interface RetrieveOrderUseCase {

    List<Order> findAllOrders();

    Optional<Order> findOrderById(Long id) throws OrderException;

}
