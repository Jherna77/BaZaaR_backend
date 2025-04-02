package com.jhernandez.backend.bazaar.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.models.Product;

public interface ProductRepositoryPort {

    Optional<Product> createProduct(Product product);

    List<Product> getAllProducts();

    // List<Product> getProductsByCategoryId(Long categoryId);

    // List<Product> getProductsByUserId(Long userId);

    Optional<Product> getProductById(Long id);

    // Optional<Product> updateProduct(Product product);
    Optional<Product> updateProduct(Long id, Product product);

    boolean deleteProductById(Long id);

    // boolean deleteProducts();

}
