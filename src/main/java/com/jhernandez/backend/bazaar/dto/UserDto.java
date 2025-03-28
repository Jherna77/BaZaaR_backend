package com.jhernandez.backend.bazaar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    // private String roles;
    private List<String> roles;
}
