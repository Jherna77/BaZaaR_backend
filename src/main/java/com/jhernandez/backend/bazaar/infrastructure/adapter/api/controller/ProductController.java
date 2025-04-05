package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.model.Product;

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

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        log.info("Creating product: {}", product.getName());
        try {
            return ResponseEntity.ok(productService.createProduct(product));
        } catch (ProductException e) {
            log.error("Error creating product: {}", product.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Product> findAllPoducts() {
        log.info("Finding all products");
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        log.info("Finding product with ID {}", id);
        try {
            return productService.findProductById(id).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ProductException e) {
            log.error("Error getting product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        log.info("Updating product with ID {}", id);
        product.setId(id);
        try {
            return productService.updateProduct(product).map(ResponseEntity::ok)
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
