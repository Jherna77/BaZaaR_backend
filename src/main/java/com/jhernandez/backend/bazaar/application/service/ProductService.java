package com.jhernandez.backend.bazaar.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.ImageStoragePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;

import lombok.RequiredArgsConstructor;

/**
 * This class implements the ProductServicePort interface, which defines the
 * contract for product-related operations.
 * It can include methods for creating, retrieving, updating, and deleting
 * products.
 * The actual implementation of these methods would depend on the specific
 * requirements of the application.
 * The ProductService class is responsible for implementing the business logic
 * related to products and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on products and
 * handles any exceptions that may occur.
 */
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;
    private final ImageStoragePort imageStoragePort;

    @Override
    public Optional<Product> createProduct(Product product, List<ImageFile> productImages) {
        product.setImagesUrl(imageStoragePort.saveImagesList(productImages)
                .stream()
                .map(imageFile -> imageFile.getImageUrl())
                .toList());
        return productRepositoryPort.saveProduct(product);
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
    public Optional<Product> updateProduct(Product product, List<ImageFile> productsImages) throws ProductException {
        Product existingProduct = findProductById(product.getId())
                .orElseThrow(() -> new ProductException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategories(product.getCategories());

        List<String> finalImages = new ArrayList<>(product.getImagesUrl());
        imageStoragePort.deleteImageListByUrl(
                existingProduct.getImagesUrl().stream()
                        .filter(img -> !finalImages.contains(img))
                        .toList());
        if (productsImages != null && !productsImages.isEmpty()) {
            finalImages.addAll(
                    imageStoragePort.saveImagesList(productsImages).stream()
                            .map(ImageFile::getImageUrl)
                            .toList());
        }
        existingProduct.setImagesUrl(finalImages);

        return productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public Optional<Product> enableProductById(Long id) throws ProductException {
        Product existingProduct = findProductById(id)
                .orElseThrow(() -> new ProductException("Product not found"));

        existingProduct.setEnabled(true);
        return productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public Optional<Product> disableProductById(Long id) throws ProductException {
        Product existingProduct = findProductById(id)
                .orElseThrow(() -> new ProductException("Product not found"));

        existingProduct.setEnabled(false);
        return productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void deleteProductById(Long id) throws ProductException {
        productRepositoryPort.deleteProductById(id);
    }

}
