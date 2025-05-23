package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private Product product;
    private User user;
    private String comment;
    private Integer rating;
    private LocalDateTime reviewDate;

    public Review(Long id, Product product, User user, String comment, Integer rating, LocalDateTime reviewDate) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }
    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    
    
}
