package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.RequiredField;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.UniqueCategoryName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private Boolean enabled;

    @UniqueCategoryName
    @RequiredField
    private String name;

    private String imageUrl;

}
