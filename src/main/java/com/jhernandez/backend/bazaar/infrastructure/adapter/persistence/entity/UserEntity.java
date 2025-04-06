package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

// import com.jhernandez.backend.bazaar.validation.UniqueEmail;
// import com.jhernandez.backend.bazaar.validation.Password;
// import com.jhernandez.backend.bazaar.validation.RequiredField;
// import com.jhernandez.backend.bazaar.validation.ZipCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
// import jakarta.persistence.Transient;
// import jakarta.persistence.UniqueConstraint;
// import jakarta.validation.constraints.Email;
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

    // @Transient // El atributo no se persistirá en la BD
    // private boolean isAdmin;

    // @Transient
    // private boolean isShop;

    private boolean enabled;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }
}
