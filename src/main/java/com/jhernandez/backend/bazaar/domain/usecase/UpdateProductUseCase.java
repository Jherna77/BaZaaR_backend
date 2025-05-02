package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;

public interface UpdateProductUseCase {

    Optional<Product> updateProduct(Product product, List<ImageFile> productImages) throws ProductException, ImageFileException;

    void saveProduct (Product product);
    
    // Optional<Product> updateProductImages(Product product, List<ImageFile> productImages) throws ProductException, ImageFileException;
        
    Optional<Product> enableProductById(Long id) throws ProductException;

    Optional<Product> disableProductById(Long id) throws ProductException;

}
