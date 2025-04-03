package com.jhernandez.backend.bazaar.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.entities.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
