package com.jhernandez.backend.bazaar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.dto.ProductDto;
import com.jhernandez.backend.bazaar.entities.ProductEntity;
import com.jhernandez.backend.bazaar.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity view(@PathVariable Long id) {
        return productService.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(@RequestBody ProductEntity product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
        // return ResponseEntity.ok(service.save(product));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long id, @RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
