package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import java.util.Optional;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* 
 * Validates if the email already exists in the database
 * It is used in the registration and update of users to validate the email
 */
@Slf4j
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

    private final JpaUserRepository userRepository;
    private final HttpServletRequest request;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        log.info("Validating unique email: {}", email);
        
        return request.getMethod().equals("PUT") ? isValidForUpdate(email) : !userRepository.existsByEmail(email);
    }

    /*
     * In case of an update, we need to check if the email is the same as the one in the database for the user
     * If it is the same, we don't need to validate it again
     * If it is not the same, we need to check if it already exists in the database
     * If it is not the same and it doesn't exist, we can update the user with the new email
     */
    private boolean isValidForUpdate(String email) {
        
        Long id = Long.parseLong(request.getRequestURI()
            .substring(request.getRequestURI().lastIndexOf('/') + 1));

        Optional<String> existingEmail = userRepository.findById(id).map(user -> user.getEmail());

        return existingEmail.map
            (existing -> existing.equals(email) || !userRepository.existsByEmail(email))
            .orElse(!userRepository.existsByEmail(email));
    }

}