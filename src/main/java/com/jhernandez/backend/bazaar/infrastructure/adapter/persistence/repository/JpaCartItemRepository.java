package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CartItemEntity;

public interface JpaCartItemRepository extends JpaRepository<CartItemEntity, Long> {

}
