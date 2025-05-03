package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CategoryEntity;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);

    @Query("SELECT c FROM CategoryEntity c WHERE c.enabled = true")
    List<CategoryEntity> findAllEnabled();

}
