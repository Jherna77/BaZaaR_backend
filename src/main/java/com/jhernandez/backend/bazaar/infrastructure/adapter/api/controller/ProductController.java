package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
// import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ProductDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.ValidationUtils.fieldValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductServicePort productService;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto product, BindingResult result) {
        log.info("Creating product: {}", product.getName());
        try {
            return (result.hasErrors())
                ? fieldValidation(result) 
                : ResponseEntity.status(HttpStatus.CREATED)
                    .body(productService.createProduct(productDtoMapper.toDomain(product))
                    .map(productDtoMapper::toDto));
        } catch (ProductException e) {
            log.error("Error creating product: {}", product.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<ProductDto> findAllPoducts() {
        log.info("Finding all products");
        return productService.findAllProducts().stream()
            .map(productDtoMapper::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> findProductsByCategoryId(@PathVariable Long categoryId) {
        log.info("Finding products by category with ID {}", categoryId);
        try {
            return ResponseEntity.ok(productService.findProductsByCategoryId(categoryId).stream()
                    .map(productDtoMapper::toDto)
                    .collect(Collectors.toList()));
        } catch (DomainException e) {
            log.error("Error getting products by category with ID {}", categoryId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        // } catch (CategoryException e) {
        //     log.error("Error getting products by category with ID {}", categoryId);
        //     return ResponseEntity.badRequest().body(e.getMessage());
        // }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        log.info("Finding product with ID {}", id);
        try {
            return productService.findProductById(id).map(productDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ProductException e) {
            log.error("Error getting product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDto product, BindingResult result, @PathVariable Long id) {
        log.info("Updating product with ID {}", id);
        product.setId(id);
        try {
            return (result.hasErrors())
                ? fieldValidation(result) 
                : productService.updateProduct(productDtoMapper.toDomain(product))
                    .map(productDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ProductException e) {
            log.error("Error updating product: {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with ID {}", id);
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (ProductException e) {
            log.error("Error deleting product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
