package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.application.service.ImageService;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CategoryDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.CategoryDtoMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ImageFileDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CATEGORIES;
import static com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.ValidationUtils.fieldValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * CategoryController is a REST controller that handles HTTP requests related to categories.
 * It provides endpoints for creating, retrieving, updating, and deleting categories.
 * The controller uses the CategoryServicePort to perform the operations and the CategoryDtoMapper to convert between domain models and DTOs.
 */
@RestController
@RequestMapping(CATEGORIES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryServicePort categoryService;
    private final CategoryDtoMapper categoryDtoMapper;
    private final ImageService imageService;
    private final ImageFileDtoMapper imageFileDtoMapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createCategory(
            @RequestPart("category") @Valid CategoryDto category,
            BindingResult result,
            @RequestPart("image") MultipartFile imageFile) {

        log.info("Creating category: {}", category.getName());

        try {
            // Save image and update URL
            String imageUrl = imageService.saveImage(imageFileDtoMapper.toDomain((imageFile))).getImageUrl();
            category.setImageUrl(imageUrl);

            return (result.hasErrors())
                ? fieldValidation(result) 
                : ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoryService.createCategory(categoryDtoMapper.toDomain(category))
                    .map(categoryDtoMapper::toDto));

        } catch (CategoryException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // @PostMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto category, BindingResult result) {
    //     log.info("Creating category: {}", category.getName());
    //     try {
    //         return (result.hasErrors())
    //             ? fieldValidation(result) 
    //             : ResponseEntity.status(HttpStatus.CREATED)
    //                 .body(categoryService.createCategory(categoryDtoMapper.toDomain(category))
    //                 .map(categoryDtoMapper::toDto));
    //     } catch (CategoryException e) {
    //         log.error("Error creating category: {}", category.getName());
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }        
    // }

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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateCategory(
        @RequestPart("category") @Valid CategoryDto category,
        BindingResult result,
        @RequestPart(value = "image", required = false) MultipartFile imageFile,
        @PathVariable Long id) {
        
        log.info("Updating category with ID {}", category.getName());
        category.setId(id);
        try {

            // Check if image is provided and save image and update URL
            if (imageFile != null && !imageFile.isEmpty()) {
                // imageService.deleteImage(category.getImageUrl());
                String imageUrl = imageService
                    .saveImage(imageFileDtoMapper.toDomain(imageFile))
                    .getImageUrl();
                category.setImageUrl(imageUrl);
            }

            return (result.hasErrors())
                ? fieldValidation(result) 
                : categoryService.updateCategory(categoryDtoMapper.toDomain(category))
                        .map(categoryDtoMapper::toDto)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
                
        } catch (CategoryException | IOException e) {
            log.error("Error updating category with ID {}", category.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto category, BindingResult result, @PathVariable Long id) {
    //     log.info("Updating category with ID {}", category.getName());
    //     category.setId(id);
    //     try {
    //         return (result.hasErrors())
    //             ? fieldValidation(result) 
    //             : categoryService.updateCategory(categoryDtoMapper.toDomain(category))
    //                     .map(categoryDtoMapper::toDto)
    //                     .map(ResponseEntity::ok)
    //                     .orElse(ResponseEntity.notFound().build());
                
    //     } catch (CategoryException e) {
    //         log.error("Error updating category with ID {}", category.getName());
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
