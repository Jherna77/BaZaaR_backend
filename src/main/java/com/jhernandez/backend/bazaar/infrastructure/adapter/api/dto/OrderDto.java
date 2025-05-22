package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private ItemDto item;
    private Long customerId;
    private Long shopId;
    private String orderDate;

}
