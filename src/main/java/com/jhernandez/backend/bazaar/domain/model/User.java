package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
import com.jhernandez.backend.bazaar.domain.exception.UserException;

public class User {

    private Long id;
    private Boolean enabled;
    private UserRole role;
    private String email;
    private String password;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private List<Product> shop;
    private List<Item> cart;
    private List<Order> orders;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, Boolean enabled, UserRole role, String email, String password, String name, String surnames,
            String address, String city, String province, String zipCode, List<Product> shop, List<Item> cart,
            List<Order> orders) {
        this.id = id;
        this.enabled = enabled;
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surnames = surnames;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.shop = shop;
        this.cart = cart;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<Product> getShop() {
        return shop;
    }

    public List<Item> getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
        if (this.shop != null)
            this.shop.forEach(product -> product.setEnabled(enabled));
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setShop(List<Product> shop) {
        this.shop = shop;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void enable() {
        this.enabled = true;
        if (this.shop != null)
            this.shop.forEach(product -> product.enable());
    }

    public void disable() throws UserException {
        if (!this.enabled)
            throw new UserException(ErrorCode.USER_ALREADY_DISABLED);
        this.enabled = false;
        if (this.shop != null)
            this.shop.forEach(product -> product.disable());
    }

    public void addProductToShop(Product product) throws UserException {
        for (Product shopProduct : this.shop) {
            if (shopProduct.getId().equals(product.getId())) {
                throw new UserException(ErrorCode.SHOP_PRODUCT_ALREADY_EXISTS);
            }
        }
        this.shop.add(product);
    }

    public void removeProductFromShop(Long productId) throws UserException {
        this.shop.removeIf(product -> product.getId().equals(productId));
    }

    public void updateProductInShop(Product product) throws UserException {
        for (Product shopProduct : this.shop) {
            if (shopProduct.getId().equals(product.getId())) {
                this.shop.remove(shopProduct);
                this.shop.add(product);
                return;
            }
        }
        throw new UserException(ErrorCode.SHOP_PRODUCT_NOT_FOUND);
    }

    public void addItemToCart(Item item) throws UserException {
        for (Item cartItem : this.cart) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())) {
                throw new UserException(ErrorCode.CART_ITEM_ALREADY_EXISTS);
            }
        }
        this.cart.add(item);
    }

    public void removeItemFromCart(Long itemId) throws UserException {
        this.cart.removeIf(item -> item.getId().equals(itemId));
    }
    
    public void createOrderFromCart() {    
        this.orders.add(new Order(
            null,
            this.cart.stream()
            .map(item -> new Item(null, item.getProduct(), item.getSalePrice(),
                                    item.getSaleShipping(), item.getQuantity(),
                                    item.getTotalPrice())).toList(),
            this,
            LocalDateTime.now()));
        this.cart.clear();
    }

}
