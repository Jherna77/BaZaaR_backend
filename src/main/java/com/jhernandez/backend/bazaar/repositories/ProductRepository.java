package com.jhernandez.backend.bazaar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
