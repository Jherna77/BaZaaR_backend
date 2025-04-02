package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;

public interface RetrieveCategoryUseCase {

    Optional<Category> getCategoryById(Long id) throws CategoryException;

    List<Category> getAllCategories();

}
