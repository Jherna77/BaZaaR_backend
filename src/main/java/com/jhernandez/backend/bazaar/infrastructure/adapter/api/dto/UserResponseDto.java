package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private Boolean enabled;
    private UserRoleDto role;
    private String email;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private List<CategoryDto> favCategories;

}