package com.jhernandez.backend.bazaar.application.usecases;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteProductUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public void deleteProduct(Long id) throws ProductException {
        productRepositoryPort.deleteProductById(id);
    }

}
