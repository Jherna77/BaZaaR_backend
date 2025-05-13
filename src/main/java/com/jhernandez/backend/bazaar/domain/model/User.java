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
    private List<Product> shop;
    private List<Item> cart;
    private List<Order> orders;

    public void addProductToShop(Product product) throws UserException {
        for (Product shopProduct : this.shop) {
            if (shopProduct.getId().equals(product.getId())) {
                throw new UserException("Product already exists in User shop");
            }
        }
        this.shop.add(product);
    }

    public void removeProductFromShop(Long productId) throws UserException {
        for (Product product : this.shop) {
            if (product.getId().equals(productId)) {
                this.shop.remove(product);
                return;
            }
        }
        throw new UserException("Product not found in user shop");
    }

    public void updateProductInShop(Product product) throws UserException {
        for (Product shopProduct : this.shop) {
            if (shopProduct.getId().equals(product.getId())) {
                this.shop.remove(shopProduct);
                this.shop.add(product);
                return;
            }
        }
        throw new UserException("Product not found in user shop");
    }

    public void addItemToCart(Item item) throws UserException {
        for (Item cartItem : this.cart) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())) {
                throw new UserException("Item already exists in user cart");
            }
        }
        this.cart.add(item);
    }

    public void removeItemFromCart(Long itemId) throws UserException {
        for (Item item : this.cart) {
            if (item.getId().equals(itemId)) {
                this.cart.remove(item);
                return;
            }
        }
        throw new UserException("Item not found in user cart");
    }
    
    public void createOrderFromCart() {    
        this.orders.add(new Order(
            null,
            this.cart.stream()
            .map(item -> new Item(null, item.getProduct(), item.getQuantity()))
            .toList()));
        this.cart.clear();
    }

}
