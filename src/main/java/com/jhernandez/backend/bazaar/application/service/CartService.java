package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.CartServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.CartItem;
import com.jhernandez.backend.bazaar.domain.model.User;

public class CartService implements CartServicePort{

    private final UserRepositoryPort userRepository;

    public CartService(UserRepositoryPort userRepository) {//, CartItemRepositoryPort cartItemRepository) {
        this.userRepository = userRepository;
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
        user.addItemToCart(item);
        userRepository.saveUser(user);
    }

    @Override
    public List<CartItem> removeItemFromCart(Long userId, Long itemId) throws UserException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        user.removeItemFromCart(itemId);
        return userRepository.saveUser(user)
                .orElseThrow(() -> new UserException("Could not save user"))
                .getCart();
    }

    @Override
    public List<CartItem> updateItemQuantity(Long userId, Long itemId, int quantity) throws UserException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        for (CartItem item : user.getCart()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                return userRepository.saveUser(user)
                .orElseThrow(() -> new UserException("Could not save user"))
                .getCart();
            }
        }
        throw new UserException("Item not found in cart");
    }

    @Override
    public List<CartItem> clearCart(Long userId) throws UserException{
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        user.getCart().clear();
        return userRepository.saveUser(user)
                .orElseThrow(() -> new UserException("Could not save user"))
                .getCart();
    }

}
