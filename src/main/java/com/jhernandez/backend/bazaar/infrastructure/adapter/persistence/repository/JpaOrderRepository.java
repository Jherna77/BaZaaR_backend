package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

}
