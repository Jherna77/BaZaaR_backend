package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Backup;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.BackupEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BackupEntityMapper {

    Backup toDomain(BackupEntity backupEntity);

    BackupEntity toEntity(Backup backup);

}
