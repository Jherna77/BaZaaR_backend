package com.jhernandez.backend.bazaar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jhernandez.backend.bazaar.entities.UserEntity;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
