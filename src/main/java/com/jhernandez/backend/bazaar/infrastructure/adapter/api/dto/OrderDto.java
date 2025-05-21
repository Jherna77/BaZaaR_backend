package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private ItemDto item;
    // private List<ItemDto> items;
    private Long customerId;
    private String orderDate;

}
