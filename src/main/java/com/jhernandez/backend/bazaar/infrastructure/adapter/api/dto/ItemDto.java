package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDto {

    private OrderDto order;
    private ProductDto product;
    private Integer quantity;

}
