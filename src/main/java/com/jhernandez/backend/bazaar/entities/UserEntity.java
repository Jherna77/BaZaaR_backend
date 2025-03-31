package com.jhernandez.backend.bazaar.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jhernandez.backend.bazaar.validation.UniqueEmail;
import com.jhernandez.backend.bazaar.validation.Password;
import com.jhernandez.backend.bazaar.validation.RequiredField;
import com.jhernandez.backend.bazaar.validation.ZipCode;

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
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
    @JoinTable(
        name="users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<RoleEntity> roles;

    @UniqueEmail
    @RequiredField
    @Email(message = "{validation.email.invalid.message}")
    @Column(unique = true)
    private String email;

    @Password
    private String password;

    @RequiredField
    private String name;

    @RequiredField
    private String surnames;

    @RequiredField
    private String address;
    
    @RequiredField
    private String city;

    @RequiredField
    private String province;

    @ZipCode
    @Column(name = "zip_code")
    private String zipCode;

    @Transient // El atributo no se persistirá en la BD
    private boolean isAdmin;

    @Transient
    private boolean isShop;

    private boolean enabled;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserEntity other = (UserEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }
}
