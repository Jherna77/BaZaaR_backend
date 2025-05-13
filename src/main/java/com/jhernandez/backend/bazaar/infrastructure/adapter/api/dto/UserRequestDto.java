package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.PasswordStrength;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.RequiredField;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.UniqueEmail;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.ZipCode;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDto {

    private Long id;
    private Boolean enabled;

    @UserRole
    private UserRoleDto role;

    @UniqueEmail
    @RequiredField
    @Email(message = "{validation.email.invalid.message}")
    private String email;

    @PasswordStrength
    private String password;
    
    @RequiredField
    private String name;

    @RequiredField
    private String surnames;

    @RequiredField
    private String address;

    @RequiredField
    private String city;

    @RequiredField
    private String province;

    @ZipCode
    private String zipCode;
    
}
