package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Product {

    private Long id;
    private Boolean enabled;
    private String name;
    private String description;
    private Double price;
    private Double shipping;
    private List<Category> categories;
    private List<String> imagesUrl;
    private User shop;
    private Double rating;
    private Integer ratingCount;
    private LocalDateTime createdAt;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, Boolean enabled, String name, String description, Double price, Double shipping,
            List<Category> categories, List<String> imagesUrl, User shop, Double rating, Integer ratingCount,
            LocalDateTime createdAt) {
        this.id = id;
        this.enabled = enabled;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shipping = shipping;
        this.categories = categories;
        this.imagesUrl = imagesUrl;
        this.shop = shop;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getShipping() {
        return shipping;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public User getShop() {
        return shop;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public void setShop(User shop) {
        this.shop = shop;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAtNow() {
        this.createdAt = LocalDateTime.now();;
    }

    public void enable() {
        this.enabled = true;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void disable() {
        this.enabled = false;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Long categoryId, Category defaultCategory) {
        this.categories
                .removeIf(category -> category.getId().equals(categoryId));
        if (this.categories.isEmpty())
            addCategory(defaultCategory);
    }

    public void addImageUrl(String imageUrl) {
        this.imagesUrl.add(imageUrl);
    }

    public void removeImageUrl(String imageUrl) {
        this.imagesUrl.remove(imageUrl);
    }

    public void calculateRating(List<Integer> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            this.rating = 0.0;
            this.ratingCount = 0;
            return;
        }
        this.ratingCount = ratings.size();
        this.rating = ratings.stream().mapToDouble(Integer::doubleValue).sum() / this.ratingCount;
    }

}

    // private BigDecimal discountPrice;
    // private BigDecimal discountRate;
    // private String imageUrl;
    // private String imageName;
    // private String imageType;
    // private Long stockQuantity;
    // private Long soldQuantity;
    // private Long createdAt;
    // private Long updatedAt;