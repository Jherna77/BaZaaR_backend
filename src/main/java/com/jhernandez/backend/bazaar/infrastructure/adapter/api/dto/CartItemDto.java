package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemDto {

    private Long id;
    private ProductDto product;
    private int quantity;

}
