package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private Order order;
    private String comment;
    private Integer rating;
    private LocalDateTime reviewDate;

    public Review(Long id, Order order, String comment, Integer rating, LocalDateTime reviewDate) {
        this.id = id;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public void setReviewDateNow() {
        this.reviewDate = LocalDateTime.now();
    }

}
