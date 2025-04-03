package com.jhernandez.backend.bazaar.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.services.CategoryService;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.models.Category;
// import com.jhernandez.backend.bazaar.domain.ports.out.CategoryServicePort;

// import static com.jhernandez.backend.bazaar.configuration.ValidationConfig.fieldValidation;

// import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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
    public List<Category> getAllCategories() {
        log.info("Getting all categories");
        return categoryService.getAllCategories();
    }

    // @PostMapping
    // public ResponseEntity<?> create(@Valid @RequestBody CategoryEntity category, BindingResult result) {
    //     log.info("Creating category: {}", category.getName());
    //     return (result.hasFieldErrors())
    //             ? fieldValidation(result)
    //             : ResponseEntity.ok(categoryService.createCategory(category));        
    // }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        log.info("Updating category with ID {}", category.getName());
        try {
            return categoryService.updateCategory(id, category).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (CategoryException e) {
            log.error("Error updating category with ID {}", category.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  

    // @PutMapping("/update/{id}")
    // public ResponseEntity<?> update(@Valid @RequestBody CategoryEntity category, BindingResult result, @PathVariable Long id) {
    //     log.info("Updating category {}", id);
    //     return (result.hasFieldErrors())
    //             ? fieldValidation(result)
    //             : categoryService.update(id, category).map(ResponseEntity::ok)
    //                 .orElse(ResponseEntity.notFound().build());
    // }    

    // @PutMapping("/disable/{id}")
    // public ResponseEntity<CategoryDto> disable(@PathVariable Long id) {
    //     log.info("Disabling category {}", id);
    //     return categoryService.disable(id).map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }


}
