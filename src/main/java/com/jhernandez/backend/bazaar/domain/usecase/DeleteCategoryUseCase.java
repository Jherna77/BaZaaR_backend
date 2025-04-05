package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;

public interface DeleteCategoryUseCase {

    void deleteCategoryById(Long id) throws CategoryException;

}
