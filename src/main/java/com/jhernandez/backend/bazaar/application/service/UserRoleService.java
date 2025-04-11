package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.UserRoleRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRoleServicePort;
import com.jhernandez.backend.bazaar.domain.model.UserRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRoleService implements UserRoleServicePort{

    private final UserRoleRepositoryPort userRoleRepositoryPort;

    @Override
    public List<UserRole> findAllUserRoles() {
        return userRoleRepositoryPort.findAllUserRoles();
    }

}
