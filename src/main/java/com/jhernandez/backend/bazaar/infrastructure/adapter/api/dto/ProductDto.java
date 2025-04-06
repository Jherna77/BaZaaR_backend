package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.RequiredField;

@Getter
@Setter
public class ProductDto {
    
    private Long id;

    @RequiredField
    private String name;

    @RequiredField
    private String description;

    @RequiredField
    private String price;
    
    private List<CategoryDto> categories;

}

