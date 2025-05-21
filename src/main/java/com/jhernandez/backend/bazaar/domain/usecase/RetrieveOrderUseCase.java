package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Order;

public interface RetrieveOrderUseCase {

    // List<Order> findAllOrders();

    List<Order> findPurchaseOrdersByUserId(Long userId) throws UserException;
    
    List<Order> findSaleOrdersByUserId(Long userId) throws UserException;

    Order findOrderById(Long id) throws OrderException;

    // Optional<List<Order>> findOrdersByUserId(Long userId) throws OrderException;

}
