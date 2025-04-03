package com.jhernandez.backend.bazaar.application.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RetrieveCategoryUseCaseImpl implements RetrieveCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Optional<Category> getCategoryById(Long id) throws CategoryException {
        return categoryRepositoryPort.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoryPort.getAllCategories();
    }

}
