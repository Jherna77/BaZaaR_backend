package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;

public interface CreateProductUseCase {

    Optional<Product> createProduct(Product product) throws ProductException;

}
