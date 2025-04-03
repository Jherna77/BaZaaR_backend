package com.jhernandez.backend.bazaar.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.services.ProductService;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.models.Product;

// import static com.jhernandez.backend.bazaar.configuration.ValidationConfig.fieldValidation;

// import jakarta.validation.Valid;
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
    public List<Product> getAllPoducts() {
        log.info("Getting all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        log.info("Getting product with ID {}", id);
        try {
            return productService.getProductById(id).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ProductException e) {
            log.error("Error getting product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        log.info("Updating product with ID {}", id);
        try {
            return productService.updateProduct(id, product).map(ResponseEntity::ok)
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
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductException e) {
            log.error("Error deleting product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // @PutMapping("/disable/{id}")
    // public ResponseEntity<ProductDto> disable(@PathVariable Long id) {
    //     log.info("Disabling product {}", id);
    //     return productService.disable(id).map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

}
