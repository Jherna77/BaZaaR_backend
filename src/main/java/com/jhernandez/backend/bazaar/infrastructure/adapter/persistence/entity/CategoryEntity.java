package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Column;

// import java.util.List;
// import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
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
    // @ManyToMany(mappedBy = "categories")
    // private List<ProductEntity> products; // Lista de productos de esta categoría (opcional, si se necesita la relación inversa)

    @Column(name = "image_url")
    private String imageUrl;
    // private String description;
    // private CategoryEntity parentCategory;
    // private List<CategoryEntity> subcategories;
    // private List<ProductEntity> products;

    private boolean enabled;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }
}
