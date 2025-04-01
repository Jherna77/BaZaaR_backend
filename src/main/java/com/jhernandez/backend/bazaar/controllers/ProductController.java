package com.jhernandez.backend.bazaar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.dto.ProductDto;
import com.jhernandez.backend.bazaar.entities.ProductEntity;
import com.jhernandez.backend.bazaar.services.ProductService;
import static com.jhernandez.backend.bazaar.configuration.ValidationConfig.fieldValidation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> list() {
        log.info("Listing all products");
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        log.info("Getting product by ID: {}", id);
        return productService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductEntity product, BindingResult result) {
        log.info("Creating product: {}", product.getName());
        return (result.hasFieldErrors())
                ? fieldValidation(result)
                : ResponseEntity.ok(productService.save(product));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductEntity product, BindingResult result, @PathVariable Long id) {
        log.info("Updating product {}", id);
        return (result.hasFieldErrors())
                ? fieldValidation(result)
                : productService.update(id, product).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<ProductDto> disable(@PathVariable Long id) {
        log.info("Disabling product {}", id);
        return productService.disable(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Deleting product {}", id);
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
