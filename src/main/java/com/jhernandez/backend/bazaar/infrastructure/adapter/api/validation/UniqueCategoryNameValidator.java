package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import java.util.Optional;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaCategoryRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
/*
 * This class is used to validate that the category name is unique in the database
 * It is used in the CategoryDto class to validate the name field
 */
@Slf4j
@RequiredArgsConstructor
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String>{

    private final JpaCategoryRepository categoryRepository;
    private final HttpServletRequest request;

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext context) {
        log.info("Validating unique category name: {}", categoryName);
        
        return request.getMethod().equals("PUT") ? isValidForUpdate(categoryName) : !categoryRepository.existsByName(categoryName);
    }

    /*
     * In case of an update, we need to check if the name is the same as the one in the database for the category
     * If it is the same, we don't need to validate it again
     * If it is not the same, we need to check if it already exists in the database
     * If it is not the same and it doesn't exist, we can update the category with the new name
     */
    private boolean isValidForUpdate(String categoryName) {
        
        Long id = Long.parseLong(request.getRequestURI()
            .substring(request.getRequestURI().lastIndexOf('/') + 1));

        Optional<String> existingName = categoryRepository.findById(id).map(category -> category.getName());

        return existingName.map
            (existing -> existing.equals(categoryName) || !categoryRepository.existsByName(categoryName))
            .orElse(!categoryRepository.existsByName(categoryName));
    }

}
