package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

public interface UpdateCategoryUseCase {

    Optional<Category> updateCategory(Category category, ImageFile imageFile) throws CategoryException, ImageFileException;

    Optional<Category> enableCategoryById(Long id) throws CategoryException;

    Optional<Category> disableCategoryById(Long id) throws CategoryException;

}
