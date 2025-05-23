package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ReviewEntity;

public interface JpaReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByProductId(Long productId);

    List<ReviewEntity> findByUserId(Long userId);

}
