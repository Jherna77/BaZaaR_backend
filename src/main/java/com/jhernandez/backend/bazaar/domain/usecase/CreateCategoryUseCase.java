package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface CreateCategoryUseCase {
    
        void createCategory(Category category, ImageFile categoryImage) throws ImageFileException;

}
