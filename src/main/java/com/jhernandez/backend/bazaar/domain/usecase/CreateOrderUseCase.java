package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.UserException;

public interface CreateOrderUseCase {

    void createOrderFromCart(Long userId) throws UserException;

}
