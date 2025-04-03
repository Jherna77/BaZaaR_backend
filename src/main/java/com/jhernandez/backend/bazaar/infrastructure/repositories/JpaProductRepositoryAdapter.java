package com.jhernandez.backend.bazaar.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.models.Product;
import com.jhernandez.backend.bazaar.domain.ports.out.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.infrastructure.entities.ProductEntity;
import com.jhernandez.backend.bazaar.infrastructure.mappers.ProductMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JpaProductRepositoryAdapter implements ProductRepositoryPort {

    @Autowired
    private JpaProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    
    @Transactional
    @Override
    public Optional<Product> createProduct(Product product) {
        log.info("Creating product {}", product.getName());
        ProductEntity productEntity = productMapper.toEntity(product);
        return Optional.of(productMapper.toDomain(
                productRepository.save(productEntity)));
    }


    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products {}");
        return productRepository.findAll().stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
                // .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> getProductById(Long id) {
        log.info("Getting product with ID {}", id);
        return productRepository.findById(id).map(productMapper::toDomain);
    }

    @Transactional
    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        log.info("Updating product with ID {}", id);
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            return productMapper.toDomain(productRepository.save(existingProduct));
        });
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        log.info("Deleting product with ID {}", id);
        productRepository.deleteById(id);
    }

}
