package com.jhernandez.backend.bazaar.application.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;
import com.jhernandez.backend.bazaar.domain.ports.in.CreateCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Optional<Category> createCategory(Category category) throws CategoryException {
        return categoryRepositoryPort.createCategory(category);
    }

}
