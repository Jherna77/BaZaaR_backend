package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.entities.ProductEntity;

public interface ProductService {

    List<ProductEntity> findAll();

    Optional<ProductEntity> findById(Long id);

    ProductEntity save(ProductEntity product);

    // ProductEntity update(Long id, ProductEntity product);

    void deleteById(Long id);
    
}
