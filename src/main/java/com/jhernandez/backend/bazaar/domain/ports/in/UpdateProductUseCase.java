package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;

public interface UpdateProductUseCase {

    Optional<Product> updateProduct(Long id, Product product) throws ProductException;

}
