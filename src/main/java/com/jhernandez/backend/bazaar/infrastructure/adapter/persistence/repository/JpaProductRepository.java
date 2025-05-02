package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.id = ?1")
    List<ProductEntity> findByCategoryId(Long categoryId);
    
    @Query("SELECT p FROM ProductEntity p JOIN p.user u WHERE u.id = ?1")
    List<ProductEntity> findByUserId(Long userId);

}
