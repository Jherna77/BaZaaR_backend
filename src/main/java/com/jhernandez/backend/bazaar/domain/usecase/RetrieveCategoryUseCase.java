package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.model.Category;

public interface RetrieveCategoryUseCase {

    List<Category> findAllCategories();

    List<Category> findAllEnabledCategories();

    Category findCategoryById(Long id) throws CategoryException;

}
