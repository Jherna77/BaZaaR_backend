package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.OrderDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ItemDtoMapper.class, DateMapper.class  })
public interface OrderDtoMapper {

    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "formatDate")
    @Mapping(source = "customer.id", target = "customerId")
    OrderDto toDto(Order order);

}
