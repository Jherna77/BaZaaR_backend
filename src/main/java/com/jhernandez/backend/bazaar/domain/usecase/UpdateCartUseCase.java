package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.CartItem;

public interface UpdateCartUseCase {

    void addItemToCart(Long userId, CartItem item) throws UserException;

    // void removeItemFromCart(Long userId, Long productId);

    // void updateItemQuantity(Long userId, Long productId, int quantity);

    // void clearCart(Long userId);

}
