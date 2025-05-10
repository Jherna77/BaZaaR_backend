package com.jhernandez.backend.bazaar.application.port;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.CartItem;

public interface CartItemRepositoryPort {

    Optional<CartItem> saveCartItem(CartItem cartItem);

    Optional<CartItem> findCartItemById(Long id);

}
