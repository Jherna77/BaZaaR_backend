package com.jhernandez.backend.bazaar.domain.model;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private List<Product> products;

    public List<Product> getProductsSafe() {
        return products == null ? Collections.emptyList() : products;
    }
    
    
}
