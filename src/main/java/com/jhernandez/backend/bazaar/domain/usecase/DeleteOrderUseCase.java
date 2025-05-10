package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;

public interface DeleteOrderUseCase {

    void deleteOrderById(Long id) throws OrderException;

}
