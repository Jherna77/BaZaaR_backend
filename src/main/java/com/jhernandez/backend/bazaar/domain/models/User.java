package com.jhernandez.backend.bazaar.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private UserRole role;
    private String email;
    private String password;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private boolean enabled;
}
