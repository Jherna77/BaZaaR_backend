package com.jhernandez.backend.bazaar.domain.model;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;
    private UserRole role;
    private String email;
    private String password;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private boolean enabled;
    private List<CartItem> cart;

    public void addItemToCart(CartItem item) throws UserException {
        for (CartItem cartItem : this.cart) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())) {
                throw new UserException("Item already exists in User cart");
            }
        }
        this.cart.add(item);
    }

    public void removeItemFromCart(Long itemId) throws UserException {
        for (CartItem item : this.cart) {
            if (item.getId().equals(itemId)) {
                this.cart.remove(item);
                return;
            }
        }
        throw new UserException("Item not found in User cart");
    }       
}
