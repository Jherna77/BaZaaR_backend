package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserRoleEntityMapper.class, 
                ProductEntityMapper.class,
                ItemEntityMapper.class,
                OrderEntityMapper.class})
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

}


// @Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
//         uses = {
//             UserRoleEntityMapper.class,
//             ProductEntityMapper.class,
//             ItemEntityMapper.class,
//             OrderEntityMapper.class
//         })
// public interface UserEntityMapper {

//     @Mapping(target = "shop", ignore = true)
//     @Mapping(target = "cart", ignore = true)
//     @Mapping(target = "orders", ignore = true)
//     User toDomain(UserEntity userEntity);

//     @Mapping(target = "shop", ignore = true)
//     @Mapping(target = "cart", ignore = true)
//     @Mapping(target = "orders", ignore = true)
//     UserEntity toEntity(User user);
// }
