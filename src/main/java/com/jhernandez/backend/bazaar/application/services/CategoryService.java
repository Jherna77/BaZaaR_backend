package com.jhernandez.backend.bazaar.application.services;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;
import com.jhernandez.backend.bazaar.domain.ports.in.CreateCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateCategoryUseCase;
// import com.jhernandez.backend.bazaar.domain.ports.out.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.domain.ports.out.CategoryServicePort;

import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// This class implements the CategoryServicePort interface, which defines the contract for category-related operations.
// It can include methods for creating, retrieving, updating, and deleting categories.
// The actual implementation of these methods would depend on the specific requirements of the application.
// The CategoryService class is responsible for implementing the business logic related to categories.
// It interacts with the data layer to perform CRUD operations on categories and handles any exceptions that may occur.
@AllArgsConstructor
// @Slf4j
public class CategoryService implements CategoryServicePort {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    // private final CategoryRepositoryPort categoryRepository;

    @Override
    public Optional<Category> createCategory(Category category) throws CategoryException {
        return this.createCategoryUseCase.createCategory(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) throws CategoryException {
        return this.retrieveCategoryUseCase.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.retrieveCategoryUseCase.getAllCategories();
        // log.info("Listing all categories");
        // return this.categoryRepository.getAllCategories();
    }

    @Override
    public Optional<Category> updateCategory(Long id, Category category) throws CategoryException {
        return this.updateCategoryUseCase.updateCategory(id, category);
    }

    @Override
    public boolean deleteCategory(Long id) throws CategoryException {
        return this.deleteCategoryUseCase.deleteCategory(id);
        /* this.userPersistence.deleteUserById(this.getUserById(id).
                orElseThrow(UserNotFoundException::new).getId()); */
    }
    
    
}
