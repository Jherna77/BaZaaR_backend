package com.jhernandez.backend.bazaar.domain.ports.in;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;

public interface DeleteCategoryUseCase {

    void deleteCategory(Long id) throws CategoryException;

}
