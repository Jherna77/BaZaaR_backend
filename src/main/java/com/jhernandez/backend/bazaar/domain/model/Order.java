package com.jhernandez.backend.bazaar.domain.model;

import java.util.List;

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

}
