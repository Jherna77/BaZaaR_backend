package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface CreateCategoryUseCase {
    
        Optional<Category> createCategory(Category category, ImageFile categoryImage) throws CategoryException;

}
