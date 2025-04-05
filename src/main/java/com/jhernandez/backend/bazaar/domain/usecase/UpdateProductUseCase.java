package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.model.Product;

public interface UpdateProductUseCase {

    Optional<Product> updateProduct(Product product) throws ProductException;

}
