package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;

public interface CreateCategoryUseCase {

    Optional<Category> createCategory(Category category) throws CategoryException;

}
