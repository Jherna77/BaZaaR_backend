package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRoleEntity role;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToMany
    @JoinTable(name = "user_favourite_categories",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> favCategories;

    @ManyToMany
    @JoinTable(name = "user_favourite_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> favProducts;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> shopProducts;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_user_id")
    private List<ItemEntity> cart;
    
}
