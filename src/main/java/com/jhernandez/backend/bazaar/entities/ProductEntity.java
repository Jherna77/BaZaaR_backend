package com.jhernandez.backend.bazaar.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jhernandez.backend.bazaar.validation.RequiredField;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @RequiredField
    private String name;

    @RequiredField
    private String description;

    @ManyToMany
    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @JoinTable(name="products_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"product_id", "category_id"})})  
    private List<CategoryEntity> categories;

    @NotNull
    private Double price;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "discount_rate")
    private Double discountRate;

    // private List<String> imagesUrl;

    private boolean enabled;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + ((id == null) ? 0 : id.hashCode());
    //     result = prime * result + ((name == null) ? 0 : name.hashCode());
    //     return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     ProductEntity other = (ProductEntity) obj;
    //     if (id == null) {
    //         if (other.id != null)
    //             return false;
    //     } else if (!id.equals(other.id))
    //         return false;
    //     if (name == null) {
    //         if (other.name != null)
    //             return false;
    //     } else if (!name.equals(other.name))
    //         return false;
    //     return true;
    // }

}
