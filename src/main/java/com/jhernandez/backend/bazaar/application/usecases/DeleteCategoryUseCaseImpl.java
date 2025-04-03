package com.jhernandez.backend.bazaar.application.usecases;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public void deleteCategory(Long id) throws CategoryException {
        categoryRepositoryPort.deleteCategoryById(id);
    }
    
}
