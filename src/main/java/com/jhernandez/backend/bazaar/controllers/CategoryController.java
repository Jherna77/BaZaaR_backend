package com.jhernandez.backend.bazaar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.dto.CategoryDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.services.CategoryService;
import static com.jhernandez.backend.bazaar.configuration.ValidationConfig.fieldValidation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> list() {
        log.info("Listing all categories");
        return categoryService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryEntity category, BindingResult result) {
        log.info("Creating category: {}", category.getName());
        return (result.hasFieldErrors())
                ? fieldValidation(result)
                : ResponseEntity.ok(categoryService.save(category));        
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryEntity category, BindingResult result, @PathVariable Long id) {
        log.info("Updating category {}", id);
        return (result.hasFieldErrors())
                ? fieldValidation(result)
                : categoryService.update(id, category).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }    

    @PutMapping("/disable/{id}")
    public ResponseEntity<CategoryDto> disable(@PathVariable Long id) {
        log.info("Disabling category {}", id);
        return categoryService.disable(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
