package com.jhernandez.backend.bazaar.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.models.Category;

public interface CategoryRepositoryPort {

    Optional<Category> createCategory(Category category);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    // Optional<Category> updateCategory(Category category);
    Optional<Category> updateCategory(Long id, Category category);

    void deleteCategoryById(Long id);

    // boolean deleteCategories();

}
