package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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

    private boolean enabled;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<ItemEntity> cart;

}
