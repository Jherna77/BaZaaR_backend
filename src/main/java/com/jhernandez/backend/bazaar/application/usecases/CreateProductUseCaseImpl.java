package com.jhernandez.backend.bazaar.application.usecases;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;
import com.jhernandez.backend.bazaar.domain.ports.in.CreateProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> createProduct(Product product) throws ProductException {
        return productRepositoryPort.createProduct(product);
    }

}
