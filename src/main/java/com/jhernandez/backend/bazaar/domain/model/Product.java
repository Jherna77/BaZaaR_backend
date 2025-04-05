package com.jhernandez.backend.bazaar.domain.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<Category> categories;
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
}
