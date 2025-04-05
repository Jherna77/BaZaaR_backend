package com.jhernandez.backend.bazaar.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.jhernandez.backend.bazaar.validation.RequiredField;

// import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @RequiredField
    private String name;

    // @RequiredField
    private String description;

    @ManyToMany
    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @JoinTable(name="products_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"product_id", "category_id"})})  
    private List<CategoryEntity> categories;

    @NotNull
    private BigDecimal price;

    // @Column(name = "discount_price")
    // private Double discountPrice;

    // @Column(name = "discount_rate")
    // private Double discountRate;

    // private List<String> imagesUrl;

    // private boolean enabled;

    // @PrePersist
    // public void prePersist() {
    //     this.enabled = true;
    // }
}
