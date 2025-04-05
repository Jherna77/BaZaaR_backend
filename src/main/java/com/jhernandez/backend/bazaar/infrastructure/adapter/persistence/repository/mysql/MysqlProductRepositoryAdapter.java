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
    public Optional<Product> createProduct(Product product) {
        log.info("Creating product {}", product.getName());
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
                // .toList();
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
    public List<Product> findProductsByUserId(Long userId) {
        log.info("Finding all products by user with ID {}", userId);

        throw new UnsupportedOperationException("Unimplemented method 'findProductsByUserId'");
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findProductById(Long id) {
        log.info("Finding product with ID {}", id);
        return productRepository.findById(id).map(productEntityMapper::toDomain);
    }

    @Transactional
    @Override
    public Optional<Product> updateProduct(Product product) {
        log.info("Updating product {}", product.getName());
        return productRepository.findById(product.getId()).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            return productEntityMapper.toDomain(productRepository.save(existingProduct));
        });
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        log.info("Deleting product with ID {}", id);
        productRepository.deleteById(id);
    }

}
