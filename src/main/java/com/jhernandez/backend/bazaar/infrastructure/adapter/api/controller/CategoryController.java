package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CategoryDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.CategoryDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * CategoryController is a REST controller that handles HTTP requests related to categories.
 * It provides endpoints for creating, retrieving, updating, and deleting categories.
 * The controller uses the CategoryServicePort to perform the operations and the CategoryDtoMapper to convert between domain models and DTOs.
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryServicePort categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        log.info("Creating category: {}", category.getName());
        try {
            return ResponseEntity.ok(
                categoryService.createCategory(category).map(categoryDtoMapper::toDto));
        } catch (CategoryException e) {
            log.error("Error creating category: {}", category.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }        
    }

    @GetMapping
    public List<CategoryDto> findAllCategories() {
        log.info("Finding all categories");
        return categoryService.findAllCategories().stream()
            .map(categoryDtoMapper::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {
        log.info("Finding category with ID {}", id);
        try {
            return categoryService.findCategoryById(id).map(categoryDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (CategoryException e) {
            log.error("Error getting category with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        log.info("Updating category with ID {}", category.getName());
        category.setId(id);
        try {
            return categoryService.updateCategory(category).map(categoryDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (CategoryException e) {
            log.error("Error updating category with ID {}", category.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with ID {}", id);
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryException e) {
            log.error("Error deleting category with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
