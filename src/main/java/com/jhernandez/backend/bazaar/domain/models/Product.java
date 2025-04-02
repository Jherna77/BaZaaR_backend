package com.jhernandez.backend.bazaar.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    List<Category> categories;
    // private Double discountPrice;
    // private Double discountRate;
    // private String imageUrl;
    // private String imageName;
    // private String imageType;
    // private Long stockQuantity;
    // private Long soldQuantity;
    // private Long createdAt;
    // private Long updatedAt;

}
