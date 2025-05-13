package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.model.UserRole;

public interface RetrieveUserRoleUseCase {
    
    List<UserRole> findAllUserRoles();

    UserRole findUserRoleById(Long id);

}
