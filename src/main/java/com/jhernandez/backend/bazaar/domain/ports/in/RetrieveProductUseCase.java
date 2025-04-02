package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;

public interface RetrieveProductUseCase {

    Optional<Product> getProductById(Long id) throws ProductException;

    List<Product> getAllProducts();

}
