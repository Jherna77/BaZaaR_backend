package com.jhernandez.backend.bazaar.application.usecases;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetrieveProductUseCaseImpl implements RetrieveProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> getProductById(Long id) throws ProductException {
        return productRepositoryPort.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.getAllProducts();
    }
    

}
