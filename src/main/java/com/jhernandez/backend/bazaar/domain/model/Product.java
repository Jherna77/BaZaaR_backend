package com.jhernandez.backend.bazaar.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Long id;
    private User user;
    private String name;
    private String description;
    private Double price;
    private List<Category> categories;
    private List<String> imagesUrl;
    private boolean enabled;

    // private BigDecimal discountPrice;
    // private BigDecimal discountRate;
    // private String imageUrl;
    // private String imageName;
    // private String imageType;
    // private Long stockQuantity;
    // private Long soldQuantity;
    // private Long createdAt;
    // private Long updatedAt;

    public void addCategory(Category category) {
        this.categories.add(category);
    }
}
