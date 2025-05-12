package com.jhernandez.backend.bazaar.domain.model;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;

public class Order {

    private Long id;
    private User user;
    private List<Item> items;

    public Order(Long id, User user, List<Item> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }
    
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // public void addItem(Product product) {
    //     this.items.add(new Item(this, product, 1));
    // }
    
    public void removeItem(Long productId) throws OrderException {
        for (Item item : this.items) {
            if (item.getProduct().getId().equals(productId)) {
                this.items.remove(item);
                return;
            }
        }
        throw new OrderException("Item not found in order");
    }

    public void updateItemQuantity(Long productId, int quantity) throws OrderException {
        for (Item item : this.items) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                return;
            }
        }
        throw new OrderException("Item with product ID not found");
    }
    

}
