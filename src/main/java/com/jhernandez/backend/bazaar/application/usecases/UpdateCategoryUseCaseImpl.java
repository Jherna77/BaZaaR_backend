package com.jhernandez.backend.bazaar.application.usecases;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase{

    private final CategoryRepositoryPort categoryRepository;

    @Override
    public Optional<Category> updateCategory(Long id, Category category) throws CategoryException {
        return categoryRepository.updateCategory(id, category);
    }

}
