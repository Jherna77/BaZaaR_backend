package com.jhernandez.backend.bazaar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jhernandez.backend.bazaar.entities.RoleEntity;

// @RepositoryRestResource(path = "roles")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);

}
