package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

public class Order {

    private Long id;
    private Item item;
    // private List<Item> items;
    private User customer;
    private LocalDateTime orderDate;

    public Order(Long id, Item item, User customer, LocalDateTime orderDate) {
        this.id = id;
        this.item = item;
        this.customer = customer;
        this.orderDate = orderDate;
    }

    // public Order(Long id, List<Item> items, User customer, LocalDateTime orderDate) {
    //     this.id = id;
    //     this.items = items;
    //     this.customer = customer;
    //     this.orderDate = orderDate;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    // public List<Item> getItems() {
    //     return items;
    // }

    // public void setItems(List<Item> items) {
    //     this.items = items;
    // }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Order createOrderFromItem(Item item, User customer) {
        return new Order(null, item, customer, LocalDateTime.now());
    }

}
