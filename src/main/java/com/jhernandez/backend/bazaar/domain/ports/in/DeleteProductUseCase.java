package com.jhernandez.backend.bazaar.domain.ports.in;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;

public interface DeleteProductUseCase {

    boolean deleteProduct(Long id) throws ProductException;

}
