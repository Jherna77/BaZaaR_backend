package com.jhernandez.backend.bazaar.services;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import com.jhernandez.backend.bazaar.dto.CategoryDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;

public interface CategoryService {

    List<CategoryDto> findAll();

    // Optional<CategoryEntity> findById(Long id);

    CategoryDto save(CategoryEntity category);
    
    Optional<CategoryDto> update(Long id, CategoryEntity category);

    Optional<CategoryDto> disable(Long id);

    // void deleteById(Long id);

}
