package com.jhernandez.backend.bazaar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private boolean enabled;
    private String name;
    private String description;
    private String price;
    private String discountPrice;
    private String discountRate;
    private List<String> categories;

}
