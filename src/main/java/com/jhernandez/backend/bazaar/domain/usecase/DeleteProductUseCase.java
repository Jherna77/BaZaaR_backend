package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;

public interface DeleteProductUseCase {
    
    void deleteProductById(Long id) throws ProductException, ImageFileException;

    void deleteProductsByUserId(Long userId) throws UserException, ImageFileException;

}
