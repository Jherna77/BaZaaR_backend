package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    private String name;
    private String description;
    private String price;
    private List<CategoryDto> categories;

}

