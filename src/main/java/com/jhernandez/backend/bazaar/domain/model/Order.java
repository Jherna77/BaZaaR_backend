package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

public class Order {

    private Long id;
    private Item item;
    private User customer;
    private User shop;
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(Long id, Item item, User customer, User shop, LocalDateTime orderDate) {
        this.id = id;
        this.item = item;
        this.customer = customer;
        this.shop = shop;
        this.orderDate = orderDate;
    }

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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getShop() {
        return shop;
    }

    public void setShop(User shop) {
        this.shop = shop;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

}
