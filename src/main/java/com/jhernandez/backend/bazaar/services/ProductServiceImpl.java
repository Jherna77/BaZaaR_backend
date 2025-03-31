package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.dto.ProductDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.entities.ProductEntity;
import com.jhernandez.backend.bazaar.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    // @Transactional
    // @Override
    // public ProductEntity update(Long id, ProductEntity product) {
    //     return repository.save(product);
    // }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto convertToDto(ProductEntity product) {
    return new ProductDto(
        product.isEnabled(),
        product.getName(),
        product.getDescription(),
        product.getPrice().toString(),
        product.getDiscountPrice().toString(),
        product.getDiscountRate().toString(),
        product.getCategories().stream()
            .map(CategoryEntity::getName)
            .collect(Collectors.toList()));
    }
}
