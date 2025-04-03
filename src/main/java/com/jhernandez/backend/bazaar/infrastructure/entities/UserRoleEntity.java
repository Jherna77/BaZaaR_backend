package com.jhernandez.backend.bazaar.infrastructure.entities;

// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
