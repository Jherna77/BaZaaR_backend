package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private ProductDto product;
    private Integer quantity;

}
