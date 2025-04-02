package com.jhernandez.backend.bazaar.application.usecases;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> updateProduct(Long id, Product product) throws ProductException {
        return productRepositoryPort.updateProduct(id, product);
    }

}
