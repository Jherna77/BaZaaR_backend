package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.ProductEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    
    @Transactional
    @Override
    public Optional<Product> saveProduct(Product product) {
        log.info("Saving product {}", product.getName());
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        return Optional.of(productEntityMapper.toDomain(
                productRepository.save(productEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        log.info("Finding all products {}");
        return productRepository.findAll().stream()
                .map(productEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllEnabledProducts() {
        log.info("Finding all enabled products {}");
        return productRepository.findAllEnabled().stream()
                .map(productEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        log.info("Finding all products by category with ID {}", categoryId);
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findEnabledProductsByCategoryId(Long categoryId) {
        log.info("Finding all products by category with ID {}", categoryId);
        return productRepository.findEnabledByCategoryId(categoryId).stream()
                .map(productEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findProductsByUserId(Long userId) {
        log.info("Finding all products by user with ID {}", userId);
        return productRepository.findByUserId(userId).stream()
                .map(productEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findProductById(Long id) {
        log.info("Finding product with ID {}", id);
        return productRepository.findById(id).map(productEntityMapper::toDomain);
    }

    @Transactional
    @Override
    public void enableProductsByUserId(Long userId) {
        log.info("Enabling products with user ID {}", userId);
        productRepository.enableProductsByUserId(userId);
    }

    @Transactional
    @Override
    public void disableProductsByUserId(Long userId){ 
        log.info("Disabling products with user ID {}", userId);
        productRepository.disableProductsByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        log.info("Deleting product with ID {}", id);
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteProductsByUserId(Long userId) {
        log.info("Deleting products with user ID {}", userId);

        productRepository.deleteProductsByUserId(userId);
    }



}
