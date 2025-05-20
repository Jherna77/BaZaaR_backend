package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Item;

public interface RetrieveSaleUseCase {

    List<Item> getUserSales(Long userId) throws UserException;

    // List<Item> getUserSalesByDate(Long userId, LocalDateTime startDate, LocalDateTime endDate) throws UserException;

    // List<Item> getUserSalesByProduct(Long userId, Long productId) throws UserException;

    // List<Item> getUserSalesByStatus(Long userId, OrderStatus status) throws UserException;

    // List<Item> getUserSalesByDateAndStatus(Long userId, LocalDateTime startDate, LocalDateTime endDate,
    //         OrderStatus status) throws UserException;

}
