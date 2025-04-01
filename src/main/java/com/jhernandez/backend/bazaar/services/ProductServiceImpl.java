package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.dto.ProductDto;
import com.jhernandez.backend.bazaar.entities.ProductEntity;
import com.jhernandez.backend.bazaar.mappers.ProductMapper;
import com.jhernandez.backend.bazaar.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
            .map(productMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public ProductDto save(ProductEntity product) {
        return productMapper.toDto(saveEntity(product));
    }

    @Transactional
    private ProductEntity saveEntity(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<ProductDto> update(Long id, ProductEntity product) {
        return productRepository.findById(id).map(prod -> {
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            prod.setDiscountPrice(product.getDiscountPrice());
            prod.setDiscountRate(product.getDiscountRate());
            prod.setEnabled(product.isEnabled());
            return save(prod);
        });
    }

    @Override
    @Transactional
    public Optional<ProductDto> disable(Long id) {
        return productRepository.findById(id).map(prod -> {
            prod.setEnabled(false);
            return save(prod);
        });
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
