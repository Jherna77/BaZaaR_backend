package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;

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
    private final ImageServicePort imageServicePort;
    private final ProductServicePort productServicePort;

    @Override
    public Optional<Category> createCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException {
        // Save image in storage and update URL
        category.setImageUrl(imageServicePort.saveImage(categoryImage).getImageUrl());
        return categoryRepositoryPort.saveCategory(category);
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
    public Optional<Category> updateCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException {
        if (category.getId() == null) throw new CategoryException("Category ID must not be null");
        Category existingCategory = findCategoryById(category.getId())
                .orElseThrow(() -> new CategoryException("Category not found"));
        existingCategory.setName(category.getName());
        
        // Check if image is provided and save image /update URL
        if (categoryImage != null) {
            imageServicePort.deleteImageByUrl(existingCategory.getImageUrl());
            existingCategory.setImageUrl(imageServicePort.saveImage(categoryImage).getImageUrl());
        }
        return categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public Optional<Category> enableCategoryById(Long id) throws CategoryException {
        if (id == null) throw new CategoryException("Category ID must not be null");
        Category existingCategory = findCategoryById(id)
                .orElseThrow(() -> new CategoryException("Category not found"));
        if (existingCategory.isEnabled()) throw new CategoryException("Category is already enabled");
        existingCategory.setEnabled(true);
        return categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public Optional<Category> disableCategoryById(Long id) throws CategoryException {
        if (id == null) throw new CategoryException("Category ID must not be null");
        if (id == 1L) throw new CategoryException("This category cannot be disabled");
        Category existingCategory = findCategoryById(id)
                .orElseThrow(() -> new CategoryException("Category not found"));
        if (!existingCategory.isEnabled()) throw new CategoryException("Category is already disabled");
        existingCategory.setEnabled(false);
        return categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryException, ImageFileException, ProductException {
        if (id == null) throw new CategoryException("Category ID must not be null");
        if (id == 1L) throw new CategoryException("This category cannot be deleted");
        Category existingCategory = findCategoryById(id)
                .orElseThrow(() -> new CategoryException("Category not found"));

        List<Product> categoryProducts = productServicePort.findProductsByCategoryId(id);

        for (Product product : categoryProducts) {
            product.getCategories()
                    .removeIf(category -> category.getId().equals(id));
            if (product.getCategories().isEmpty()) {
                product.addCategory(findCategoryById(1L).orElseThrow(() -> new CategoryException("Default category not found")));
            }
            productServicePort.saveProduct(product);
        }

        if (existingCategory.getImageUrl() != null) imageServicePort.deleteImageByUrl(existingCategory.getImageUrl());
        categoryRepositoryPort.deleteCategoryById(id);
    }    

}
