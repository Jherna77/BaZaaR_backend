package com.jhernandez.backend.bazaar.application.usecases;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public boolean deleteCategory(Long id) throws CategoryException {
        return categoryRepositoryPort.deleteCategoryById(id);
    }
    
}
