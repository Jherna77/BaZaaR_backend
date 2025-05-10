package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.CartItem;

public interface UpdateCartUseCase {

    void addItemToCart(Long userId, CartItem item) throws UserException;

    List<CartItem> removeItemFromCart(Long userId, Long itemId) throws UserException;

    List<CartItem> updateItemQuantity(Long userId, Long itemId, int quantity) throws UserException;;

    List<CartItem> clearCart(Long userId) throws UserException;;

}
