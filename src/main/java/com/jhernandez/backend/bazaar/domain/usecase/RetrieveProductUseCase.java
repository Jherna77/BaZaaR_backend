package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;

public interface RetrieveProductUseCase {

    List<Product> findAllProducts();

    List<Product> findProductsByCategoryId(Long categoryId) throws CategoryException;

    List<Product> findProductsByUserId(Long userId) throws UserException;

    Optional<Product> findProductById(Long id) throws ProductException;

}
