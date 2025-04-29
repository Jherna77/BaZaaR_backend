package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;

import lombok.RequiredArgsConstructor;

/**
 * This class implements the ProductServicePort interface, which defines the contract for product-related operations.
 * It can include methods for creating, retrieving, updating, and deleting products.
 * The actual implementation of these methods would depend on the specific requirements of the application.
 * The ProductService class is responsible for implementing the business logic related to products and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on products and handles any exceptions that may occur.
 */
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> createProduct(Product product) throws ProductException {
        return productRepositoryPort.createProduct(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoryPort.findAllProducts();
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) throws ProductException, CategoryException {
        return productRepositoryPort.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByUserId(Long userId) throws ProductException, UserException {
        return productRepositoryPort.findProductsByUserId(userId);
    }

    @Override
    public Optional<Product> findProductById(Long id) throws ProductException {
        return productRepositoryPort.findProductById(id);
    }

    @Override
    public Optional<Product> updateProduct(Product product) throws ProductException {
        return productRepositoryPort.updateProduct(product);
    }

    @Override
    public Optional<Product> enableProductById(Long id) throws ProductException {
        return productRepositoryPort.enableProductById(id);
    }

    @Override
    public Optional<Product> disableProductById(Long id) throws ProductException {
        return productRepositoryPort.disableProductById(id);
    }

    @Override
    public void deleteProductById(Long id) throws ProductException {
        productRepositoryPort.deleteProductById(id);
    }

}
