package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.RequiredField;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.UniqueCategoryName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    @RequiredField
    @UniqueCategoryName
    private String name;

    // @RequiredField
    private String imageUrl;

}
