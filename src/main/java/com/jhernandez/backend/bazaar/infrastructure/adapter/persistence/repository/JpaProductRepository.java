package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.enabled = true")
    List<ProductEntity> findAllEnabled();

    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.id = ?1")
    List<ProductEntity> findByCategoryId(Long categoryId);
    
    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.id = ?1 AND p.enabled = true")    
    List<ProductEntity> findEnabledByCategoryId(Long categoryId);
    
    @Query("SELECT p FROM ProductEntity p JOIN p.user u WHERE u.id = ?1")
    List<ProductEntity> findByUserId(Long userId);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.enabled = false WHERE p.user.id = ?1")
    void disableProductsByUserId(Long userId);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.enabled = true WHERE p.user.id = ?1")
    void enableProductsByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM ProductEntity p WHERE p.user.id = ?1")
    void deleteProductsByUserId (Long userId);

}
