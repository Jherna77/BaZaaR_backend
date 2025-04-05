package com.jhernandez.backend.bazaar.domain.model;

// import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;
    // private List<Product> products;
    private boolean enabled;

}
