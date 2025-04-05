package com.jhernandez.backend.bazaar.infrastructure.persistence.entity;

import java.util.List;
// import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.jhernandez.backend.bazaar.validation.RequiredField;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // private UUID id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private String categoryId;
    private String name;

    // Relación inversa con ProductEntity
    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties({"categories", "handler", "hibernateLazyInitializer"}) 
    private List<ProductEntity> products; // Lista de productos de esta categoría (opcional, si se necesita la relación inversa)

    // private String imageUrl;
    // private String description;
    // private CategoryEntity parentCategory;
    // private List<CategoryEntity> subcategories;
    // private List<ProductEntity> products;

    // private boolean enabled;

    // @PrePersist
    // public void prePersist() {
    //     this.enabled = true;
    // }
}
