package com.jhernandez.backend.bazaar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
}
