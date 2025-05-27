package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Backup;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.BackupDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { DateMapper.class })
public interface BackupMapper {

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDate")
    BackupDto toDto(Backup backup);

}
