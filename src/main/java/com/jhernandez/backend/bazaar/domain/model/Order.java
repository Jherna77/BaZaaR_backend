package com.jhernandez.backend.bazaar.domain.model;

import java.util.List;

public class Order {

    private Long id;
    private List<Item> items;

    public Order(Long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }
    
    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }   

}
