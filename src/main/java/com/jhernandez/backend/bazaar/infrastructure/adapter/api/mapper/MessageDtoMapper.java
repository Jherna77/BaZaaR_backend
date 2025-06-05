package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Message;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.MessageDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { DateMapper.class  })
public interface MessageDtoMapper {

    @Mapping(source = "messageDate", target = "messageDate", qualifiedByName = "formatDate")
    @Mapping(source = "recipient.id", target = "recipientId")
    MessageDto toDto(Message message);

}
