package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.model.Category;

public interface UpdateCategoryUseCase {

    Optional<Category> updateCategory(Category category) throws CategoryException;

}
