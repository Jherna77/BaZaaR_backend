package com.jhernandez.backend.bazaar.infrastructure.presenter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.model.Category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryServicePort categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        log.info("Creating category: {}", category.getName());
        try {
            return ResponseEntity.ok(categoryService.createCategory(category));
        } catch (CategoryException e) {
            log.error("Error creating category: {}", category.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }        
    }

    @GetMapping
    public List<Category> findAllCategories() {
        log.info("Finding all categories");
        return categoryService.findAllCategories();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        log.info("Updating category with ID {}", category.getName());
        category.setId(id);
        try {
            return categoryService.updateCategory(category).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (CategoryException e) {
            log.error("Error updating category with ID {}", category.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  

}
