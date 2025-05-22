package com.jhernandez.backend.bazaar.domain.model;

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

    public Product(Long id, Boolean enabled, String name, String description, Double price, Double shipping,
            List<Category> categories, List<String> imagesUrl, User shop) {
        this.id = id;
        this.enabled = enabled;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shipping = shipping;
        this.categories = categories;
        this.imagesUrl = imagesUrl;
        this.shop = shop;
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

    public void enable() {
        this.enabled = true;
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