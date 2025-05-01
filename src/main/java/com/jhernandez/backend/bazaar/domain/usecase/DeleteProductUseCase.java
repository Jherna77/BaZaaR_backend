package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;

public interface DeleteProductUseCase {
    
    void deleteProductById(Long id) throws ProductException, ImageFileException;

}
