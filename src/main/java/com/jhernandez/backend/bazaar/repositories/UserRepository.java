package com.jhernandez.backend.bazaar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
