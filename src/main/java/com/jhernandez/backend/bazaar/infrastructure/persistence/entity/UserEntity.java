package com.jhernandez.backend.bazaar.infrastructure.persistence.entity;

// import java.util.List;

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
import jakarta.validation.constraints.Email;
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

    // @UniqueEmail
    // @RequiredField
    @Email(message = "{validation.email.invalid.message}")
    @Column(unique = true)
    private String email;

    // @Password
    private String password;

    // @RequiredField
    private String name;

    // @RequiredField
    private String surnames;

    // @RequiredField
    private String address;
    
    // @RequiredField
    private String city;

    // @RequiredField
    private String province;

    // @ZipCode
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
