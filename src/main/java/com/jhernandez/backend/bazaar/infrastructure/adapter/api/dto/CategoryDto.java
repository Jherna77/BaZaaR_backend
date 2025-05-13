package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private Boolean enabled;
    private String name;
    private String imageUrl;

}
