package com.jhernandez.backend.bazaar.application.usecases;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public boolean deleteProduct(Long id) throws ProductException {
        return productRepositoryPort.deleteProductById(id);
    }

}
