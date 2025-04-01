package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.dto.ProductDto;
import com.jhernandez.backend.bazaar.entities.ProductEntity;

public interface ProductService {

    List<ProductDto> findAll();

    Optional<ProductDto> findById(Long id);

    ProductDto save(ProductEntity product);

    Optional<ProductDto> update(Long id, ProductEntity product);

    Optional<ProductDto> disable(Long id);

    void deleteById(Long id);
    
}
