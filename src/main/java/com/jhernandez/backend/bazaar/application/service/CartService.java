package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.CartItemRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.CartServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.CartItem;
import com.jhernandez.backend.bazaar.domain.model.User;

public class CartService implements CartServicePort{

    private final UserRepositoryPort userRepository;
    private final CartItemRepositoryPort cartItemRepository;

    public CartService(UserRepositoryPort userRepository, CartItemRepositoryPort cartItemRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> getUserCart(Long userId) throws UserException {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"))
                .getCart();
    }

    @Override
    public void addItemToCart(Long userId, CartItem item) throws UserException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        if (user.getCart().contains(item)) {
            throw new UserException("Item already in cart");
        }
        cartItemRepository.saveCartItem(item);
        user.addItemToCart(item);
        userRepository.saveUser(user);
    }

}
