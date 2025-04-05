package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    // Relación inversa con UserEntity
    // @ManyToMany(mappedBy = "roles") 
    // @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"})
    // private List<UserEntity> users; // Lista de usuarios que tienen este rol (opcional, si se necesita la relación inversa)
}
