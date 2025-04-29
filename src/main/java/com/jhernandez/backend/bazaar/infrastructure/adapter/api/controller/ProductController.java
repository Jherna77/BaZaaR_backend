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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ARG_PRODUCT;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ARG_IMAGE;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
// import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ImageFileDtoMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ProductDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PRODUCTS;
import static com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.ValidationUtils.fieldValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(PRODUCTS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductServicePort productService;
    private final ProductDtoMapper productDtoMapper;
    private final ImageServicePort imageService;
    private final ImageFileDtoMapper imageFileDtoMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> createProduct(
        @RequestPart(ARG_PRODUCT) @Valid ProductDto product,
        BindingResult result,
        @RequestPart(ARG_IMAGE) List<MultipartFile> imageFileList) {
        log.info("Creating product: {}", product.getName());
        try {
    
            if (result.hasErrors()) {
                return fieldValidation(result);
            }

            product.setImagesUrl(saveImageList(imageFileList));

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productDtoMapper.toDomain(product))
                .map(productDtoMapper::toDto));
        } catch (ProductException | IOException e) {
            log.error("Error creating product: {}", product.getName());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @PostMapping
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    // public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto product, BindingResult result) {
    //     log.info("Creating product: {}", product.getName());
    //     try {
    //         return (result.hasErrors())
    //             ? fieldValidation(result) 
    //             : ResponseEntity.status(HttpStatus.CREATED)
    //                 .body(productService.createProduct(productDtoMapper.toDomain(product))
    //                 .map(productDtoMapper::toDto));
    //     } catch (ProductException e) {
    //         log.error("Error creating product: {}", product.getName());
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
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

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> enableProduct(@PathVariable Long id) {
        log.info("Enabling product with ID {}", id);
        try {
            return productService.enableProductById(id).map(productDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ProductException e) {
            log.error("Error enabling product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> disableProduct(@PathVariable Long id) {
        log.info("Disabling product with ID {}", id);
        try {
            return productService.disableProductById(id).map(productDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ProductException e) {
            log.error("Error disabling product with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
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

    // Saves List of images in storage and returns the URL
    private List<String> saveImageList (List<MultipartFile> imageFileList) throws IOException {
        return imageService.saveImagesList(imageFileDtoMapper.toDomainList(imageFileList))
            .stream()
            .map(imageFile -> imageFile.getImageUrl())
            .collect(Collectors.toList());
    }

}
