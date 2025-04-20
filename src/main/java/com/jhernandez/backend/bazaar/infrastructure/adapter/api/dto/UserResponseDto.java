package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String role;
    private String email;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private boolean enabled;

}