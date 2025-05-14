package com.jhernandez.backend.bazaar.application.service;

import java.util.Comparator;
import java.util.List;

import com.jhernandez.backend.bazaar.application.port.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;

/*
 * This class implements the CategoryServicePort interface, which defines the contract for category-related operations.
 * It can include methods for creating, retrieving, updating, and deleting categories.
 * The actual implementation of these methods would depend on the specific requirements of the application.
 * The CategoryService class is responsible for implementing the business logic related to categories and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on categories and handles any exceptions that may occur.
*/
public class CategoryService implements CategoryServicePort{

    private static final Long DEFAULT_CATEGORY_ID = 1L;

    private final CategoryRepositoryPort categoryRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final ImageServicePort imageServicePort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort, ProductRepositoryPort productRepositoryPort, ImageServicePort imageServicePort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
        this.imageServicePort = imageServicePort;
    }

    @Override
    public void createCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException {
        validateImage(categoryImage);
        validateCategoryForCreate(category);
        // Save image in storage and update URL
        category.setImageUrl(imageServicePort.saveImage(categoryImage).getImageUrl());
        category.setEnabled(true);
        categoryRepositoryPort.saveCategory(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepositoryPort.findAllCategories();
    }

    @Override
    public List<Category> findAllEnabledCategories() {
        return categoryRepositoryPort.findAllEnabledCategories()
            .stream()
            .sorted(Comparator.comparing(category -> category.getName().toLowerCase()))
            .toList();
    }

    @Override
    public Category findCategoryById(Long id) throws CategoryException {
        return categoryRepositoryPort.findCategoryById(id)
            .orElseThrow(() -> new CategoryException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public void updateCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException {
        validateCategoryForUpdate(category);
        Category existingCategory = findCategoryById(category.getId());
        existingCategory.setName(category.getName());
        
        // Check if image is provided and save image /update URL
        if (categoryImage != null) {
            imageServicePort.deleteImageByUrl(existingCategory.getImageUrl());
            existingCategory.setImageUrl(imageServicePort.saveImage(categoryImage).getImageUrl());
        }
        categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public void enableCategoryById(Long id) throws CategoryException {
        if (id == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        Category existingCategory = findCategoryById(id);
        if (existingCategory.getEnabled()) throw new CategoryException(ErrorCode.CATEGORY_ALREADY_ENABLED);
        existingCategory.setEnabled(true);
        categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public void disableCategoryById(Long id) throws CategoryException {
        if (id == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        if (id == DEFAULT_CATEGORY_ID)
            throw new CategoryException(ErrorCode.CATEGORY_DEFAULT_DISABLE);
        Category existingCategory = findCategoryById(id);
        if (!existingCategory.getEnabled()) throw new CategoryException(ErrorCode.CATEGORY_ALREADY_DISABLED);
        existingCategory.setEnabled(false);
        categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryException, ImageFileException, ProductException {
        if (id == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        if (id == DEFAULT_CATEGORY_ID)
            throw new CategoryException(ErrorCode.CATEGORY_DEFAULT_DELETE);
        Category existingCategory = findCategoryById(id);

        List<Product> categoryProducts = productRepositoryPort.findProductsByCategoryId(id);

        for (Product product : categoryProducts) {
            product.getCategories()
                    .removeIf(category -> category.getId().equals(id));
            if (product.getCategories().isEmpty()) {
                product.addCategory(findCategoryById(DEFAULT_CATEGORY_ID));
            }
            productRepositoryPort.saveProduct(product);
        }
        if (existingCategory.getImageUrl() != null) imageServicePort.deleteImageByUrl(existingCategory.getImageUrl());
        categoryRepositoryPort.deleteCategoryById(id);
    }

    private void validateImage(ImageFile imageFile) throws CategoryException {
        if (imageFile == null)
            throw new CategoryException(ErrorCode.CATEGORY_IMAGE_REQUIRED);
    }

    private void validateCategoryForCreate(Category category) throws CategoryException {
        if (category.getName() == null || category.getName().isEmpty())
            throw new CategoryException(ErrorCode.CATEGORY_NAME_REQUIRED);
        if (categoryRepositoryPort.existsByName(category.getName()))
            throw new CategoryException(ErrorCode.CATEGORY_NAME_EXISTS);
    }

    private void validateCategoryForUpdate(Category category) throws CategoryException {
        if (category.getId() == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        if (category.getId() == DEFAULT_CATEGORY_ID)
            throw new CategoryException(ErrorCode.CATEGORY_DEFAULT_UPDATE);
        if (!category.getEnabled())
            throw new CategoryException(ErrorCode.CATEGORY_DISABLED);
        if (category.getName() == null || category.getName().isEmpty())
            throw new CategoryException(ErrorCode.CATEGORY_NAME_REQUIRED);
        String existingName = findCategoryById(category.getId()).getName();
        if (categoryRepositoryPort.existsByName(category.getName()) && !category.getName().equals(existingName))
            throw new CategoryException(ErrorCode.CATEGORY_NAME_EXISTS);
    }

}
