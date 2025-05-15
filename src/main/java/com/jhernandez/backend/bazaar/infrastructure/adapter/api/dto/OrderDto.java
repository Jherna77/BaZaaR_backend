package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private List<ItemDto> items;
    private Long customerId;
    private String orderDate;

}
