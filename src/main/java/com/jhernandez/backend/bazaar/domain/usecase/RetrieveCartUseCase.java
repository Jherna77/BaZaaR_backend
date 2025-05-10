package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.CartItem;

public interface RetrieveCartUseCase {

    List<CartItem> getUserCart(Long userId) throws UserException;

}
