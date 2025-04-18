package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.model.Category;

// import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

/*
 * This class implements the CategoryServicePort interface, which defines the contract for category-related operations.
 * It can include methods for creating, retrieving, updating, and deleting categories.
 * The actual implementation of these methods would depend on the specific requirements of the application.
 * The CategoryService class is responsible for implementing the business logic related to categories and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on categories and handles any exceptions that may occur.
*/
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort{

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Optional<Category> createCategory(Category category) throws CategoryException {
        return categoryRepositoryPort.createCategory(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepositoryPort.findAllCategories();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) throws CategoryException {
        return categoryRepositoryPort.findCategoryById(id);
    }

    @Override
    public Optional<Category> updateCategory(Category category) throws CategoryException {
        return categoryRepositoryPort.updateCategory(category);
    }

    @Override
    public Optional<Category> enableCategoryById(Long id) throws CategoryException {
        return categoryRepositoryPort.enableCategoryById(id);
    }

    @Override
    public Optional<Category> disableCategoryById(Long id) throws CategoryException {
        return categoryRepositoryPort.disableCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryException {
        categoryRepositoryPort.deleteCategoryById(id);
    }    

}
