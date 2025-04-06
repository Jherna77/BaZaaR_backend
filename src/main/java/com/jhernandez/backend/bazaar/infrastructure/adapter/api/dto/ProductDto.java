package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.RequiredField;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDto {
    
    private Long id;

    @RequiredField
    private String name;

    @RequiredField
    private String description;

    @NotNull (message = "{validation.field.required.message}")
    @Min(value = 0, message = "{validation.product.invalid.price.message}")
    private BigDecimal price;
    
    private List<CategoryDto> categories;

}

