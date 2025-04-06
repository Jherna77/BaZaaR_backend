package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRoleDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserRoleEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRoleRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserRoleValidator implements ConstraintValidator<UserRole, UserRoleDto> {

    private final JpaUserRoleRepository roleRepository;

    @Override
    public boolean isValid(UserRoleDto userRole, ConstraintValidatorContext context) {
        log.info("Validating userRole: {}", userRole);

        UserRoleEntity existingRole = roleRepository.findById(userRole.getId()).orElse(null);
        return existingRole != null &&
                existingRole.getName().equals(userRole.getName());
        
    }

}
