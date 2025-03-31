package com.jhernandez.backend.bazaar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);

}
