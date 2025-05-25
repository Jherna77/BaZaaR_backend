package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;

public interface RetrieveProductUseCase {

    List<Product> findAllProducts();

    List<Product> findAllEnabledProducts();

    List<Product> findProductsByCategoryId(Long categoryId) throws CategoryException;
    
    List<Product> findEnabledProductsByCategoryId(Long categoryId) throws CategoryException;

    List<Product> findProductsByUserId(Long userId) throws UserException;

    List<Product> findEnabledProductsByName(String name) throws ProductException;

    List<Product> findRecentEnabledProducts();

    Product findProductById(Long id) throws ProductException;

}
